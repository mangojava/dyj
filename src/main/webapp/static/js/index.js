/**
 * 主页脚本。
 *
 * @author 林久久
 * @version 1.0 创建于2016/8/9
 */
// 初始化
$$.Map.init("mapDiv").then(function (map) {

    // 设置地图参数
    map.centerAt([119.35, 26.025]);
    map.setZoom(12);
    // 当地图实例有设置底图数据并加载完成时触发
    map.on("load", function (evt) {
        // TODO 从后台获取所有建筑大楼数据，并生成图形标注在地图上。
        findBuildings(evt.map);
    });
    // 绑定地图单击事件
    map.on("click", function (evt) {
        var g = evt.graphic;
        // 如果点击的是图形
        if (evt.graphic) {
            var attrs = evt.graphic.attributes;
            // 如果该图形有包含数据，则根据类型选择对应的处理
            if (attrs && attrs.classType) {
                // TODO 根据不同的图形执行不同的处理。
                switch (attrs.classType) {
                    // 建筑物标注
                    case "buildingMark":
                        createBuildingView(attrs);
                        break;
                    default:
                    // 没有符合的，则啥都不干
                }
            }
        }
    });

    // 创建地图控制切换组件实例
    var baseMapToggler = new $$.Map.BaseMapToggler({
        map: map
    });

    // 添加底图切换组件到地图实例中
    $("#baseMapTogglerDiv").append(baseMapToggler.getDom());

    // TODO 添加底图的方法。
    // 添加原版街道矢量底图
    baseMapToggler.append({
        id: "1",
        icon: $$.conf.getWebAppRoot() + "/lib/app/image/vector2d.jpg",// 底图项的图标
        onClick: function (itemDom, isSelected) {
            // TODO 绑定当前底图项的单击事件处理。参数 itemDom 为当前项的 dom 对象，isSelected 为当前项是否为选中状态。
            // 如果选中则切换底图
            if (isSelected) {
                map.setBasemap("streets");
            }
        }
    });
    // 添加原版影像底图
    baseMapToggler.append({
        id: "2",
        icon: $$.conf.getWebAppRoot() + "/lib/app/image/image2d.jpg",
        onClick: function (itemDom, isSelected) {
            // TODO 绑定当前底图项的单击事件处理。参数 itemDom 为当前项的 dom 对象，isSelected 为当前项是否为选中状态。
            // 如果选中则切换底图
            if (isSelected) {
                map.setBasemap("satellite");
            }
        }
    });
});

/**
 * 添加建筑物标注到地图上。
 *
 * @param data
 * @param map
 */
function addBuildingToMap(data, map) {
    require([
        "esri/symbols/SimpleMarkerSymbol",
        "esri/graphic",
        "esri/geometry/Point",
        "esri/SpatialReference",
        "esri/Color",
        "dojo/domReady!"
    ], function (SimpleMarkerSymbol, Graphic, Point, SpatialReference, Color) {
        var markSymbol = new SimpleMarkerSymbol();
        markSymbol.setColor(new Color("#FF4D4D"));
        var poi = new Point(data.lon, data.lat, new SpatialReference({wkid: 4326}));
        var attr = {
            classType: "buildingMark",// 在地图单击时，用来区别其他图形数据。
            tid: data.tid,
            buildingName: data.buildingName,
            lon: data.lon,
            lat: data.lat,
            structChartUrl: data.structChartUrl
        };
        var g = new Graphic(poi, markSymbol, attr);
        if (map.graphics) {
            map.graphics.clear();
            map.graphics.add(g);
        }
    });
}

/**
 * 获取所有建筑物数据，并加载到地图上。
 *
 * @param map
 */
function findBuildings(map) {
    $.ajax($$.conf.getWebAppRoot() + "/bs/building", {
        async: true,
        type: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            // 如果有数据，则把数据生成标注显示在地图上
            if (data && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    addBuildingToMap(data[i], map);
                }
            }
        }
    });
}

/**
 * 创建建筑物视图。
 *
 * @param buildingData 当前建筑物数据
 */
function createBuildingView(buildingData) {

    if (!buildingData || !buildingData.tid) {
        return;
    }
    var buildingId = buildingData.tid;
    $.ajax($$.conf.getWebAppRoot() + "/bs/storey/" + buildingId, {
        async: true,
        type: "GET",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            // 如果有数据，则把数据生成标注显示在地图上
            if (data && data.length > 0) {
                // 创建建筑楼层视图实例
                return new $$.Biz.BuildingStoreyView({
                    buildingData: buildingData,// 传入所属建筑大楼的数据
                    storeyData: data// 传入楼层数据
                });
            }
        }
    });
}