var svg1Width=$("#container-name").width()
var svg1Height=$("#container-name").height()

setInterval(function(){
    var date = new Date();
   var  newdate=date.toLocaleString();
    document.querySelector(".new_data").innerHTML=newdate
},1000)
for (var i=1;i<23;i++){
    $(".propelling-ball2").append("<div class='small-garden'>"+i+"</div>")
}
var RotationAngle= 360/($(".propelling-ball2 div").length)
$(".propelling-ball2 div").each(function(index,obg){
    $(obg).css("transform","rotate("+RotationAngle*index+"deg) translate(0px,-2.15rem)").text(index+1)
})
for (var j=1; j<13;j++){
    $(".cutter-disk1").append("<div class='small-garden1'>"+j+"</div>")
}
var RotationAngle1= 360/($(".cutter-disk1 div").length+1)
$(".cutter-disk1 div").each(function(index,obg){
    index= index+1
    $(obg).css("transform","rotate("+RotationAngle1*index+"deg) translate(0px,-1.4rem)")
})

var token = getCookie("user.cookie");
var target = 'ws://'+ws+'//webSocketServerTbm8hx?token='+token;
if ('WebSocket' in window) {

    var tbmData=new WebSocket(target);
}else if ('MozWebSocket' in window) {
    var tbmData = new MozWebSocket(target);
} else {
    Feng.info('当前浏览器不支持WebSocket');
    result;
}

 var traPoints = [
    {x: 0, y: svg1Height*0.15},
    {x: 0.05*svg1Width, y: svg1Height*0.15},
    {x: 0.135*svg1Width, y:svg1Height*0.4},
    {x:0.8*svg1Width, y:svg1Height*0.6},
    {x:svg1Width,y:0}
];

var TotalLenght=0;
function svgdraw(zonghuan,totaldatenumber,currentnumber,targetlist){

    var draw = SVG('container-name').size('100%', '100%')
    var trsPointsStr = new Array()

    traPoints.forEach(function (t) {
        trsPointsStr.push(t.x+','+t.y);
    });
    trsPointsStr.join(' ')

       draw.polyline(trsPointsStr+' '+svg1Width,0).fill('none').stroke({width: 5,color:'#2B3648'});
    // draw.polyline(0,svg1Height*1.5+' '+0.05*svg1Width,svg1Height*1.5).fill('none').stroke({width: 5,color:'red'});
    draw.circle(14).move(svg1Width-10,0).attr({
        'fill': '#1493E4',
        'stroke': '#fff',
        'stroke-width': 2
    }).off()
        .on('mousemove',function(e){
            var e = event || window.event;
            $(".coverage").show().css({
                "left":(e.screenX-100)*0.01+'rem',
                "top":(e.screenY-50)*0.01+'rem'
            })
            $(".dangqian-shijian").text("贯通时间2020.06.28")
            $(".suowa-huanshu").text("推进环数："+zonghuan+"环")
            $(".suowa-lishu").text("推进里数："+zonghuan*2+"米")

   }).on('mouseout',function (e) {
        $(".coverage").hide()
    })
    draw.circle(14).move(0,svg1Height*0.1).attr({
        'fill': '#1493E4',
        'stroke': '#fff',
        'stroke-width': 2,
    }).off()
        .on('mousemove',function(e){
            var e = event || window.event;
            $(".coverage").show().css({
                "left":(e.screenX)*0.01+'rem',
                "top":(e.screenY-50)*0.01+'rem'
            })
            $(".dangqian-shijian").text("启动时间2019.05.30")
            $(".suowa-huanshu").text("推进环数：0.0环")
            $(".suowa-lishu").text("推进里数：0.0米")
        }).on('mouseout',function (e) {
        $(".coverage").hide()
    })

    TotalLenght +=Math.distance(traPoints[0],traPoints[1])
    var TotalLenght1=TotalLenght
    TotalLenght +=Math.distance(traPoints[1],traPoints[2])
    var TotalLenght2=TotalLenght
    TotalLenght +=Math.distance(traPoints[2],traPoints[3])
    var TotalLenght3=TotalLenght

    TotalLenght +=Math.distance(traPoints[3],traPoints[4])
    var TotalLenght4=TotalLenght
    TotalLenght= TotalLenght/zonghuan


console.log(currentnumber)

//这个显示当前推进的环数
    if (currentnumber*TotalLenght<TotalLenght1){
        draw.polyline('0,'+svg1Height*0.15+' '+currentnumber*TotalLenght+','+svg1Height*0.15+'').fill('none').stroke({width: 5,color:'#1493e4'});
        draw.circle(14).move(currentnumber*TotalLenght,svg1Height*0.1).attr({
            'fill': '#1493E4',
            'stroke': '#fff',
            'stroke-width': 2
        }).off()
            .on('mousemove',function(e){
                var e = event || window.event;
                $(".coverage").show().css({
                    "left":(e.screenX)*0.01+'rem',
                    "top":(e.screenY)*0.01+'rem'
                })
                draw.line(currentnumber*TotalLenght+5, svg1Height*0.05,currentnumber*TotalLenght+5, 200).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
                $(".coverage").show()
                $(".dangqian-shijian").text("当前推进环数")
                $(".suowa-huanshu").text("推进环数："+currentnumber+"环")
                $(".suowa-lishu").text("推进里数："+currentnumber*2+"米")

            }).on('mouseout',function (e) {
            $("svg>line").remove()
            $(".coverage").hide()
        }).animate({duration: 1500, ease: '<>'}).fill('#00FC55').radius(7.5).animate({duration: 1500, ease: '<>'}).fill('#1493E4').radius(7).loop();
    }
    else if (currentnumber*TotalLenght>TotalLenght1&&currentnumber*TotalLenght<TotalLenght2){
        var sxy1=currentnumber*TotalLenght-TotalLenght1
        var svg2Height=((svg1Height*0.4-svg1Height*0.15)*sxy1)/(0.135*svg1Width-0.05*svg1Width)

        draw.polyline('0,'+svg1Height*0.15+' '+0.05*svg1Width+','+svg1Height*0.15+' '+currentnumber*TotalLenght+','+(svg1Height*0.15+svg2Height)+'').fill('none').stroke({width: 6,color:'#1493e4'});
        draw.circle(14).move(currentnumber*TotalLenght-5,svg1Height*0.15+svg2Height-5).attr({
            'fill': '#1493E4',
            'stroke': '#fff',
            'stroke-width': 2
        }).off()
            .on('mousemove',function(e){
                var e = event || window.event;
                $(".coverage").show().css({
                    "left":(e.screenX)*0.01+'rem',
                    "top":(e.screenY)*0.01+'rem'
                })
                draw.line(currentnumber*TotalLenght+5, svg1Height*0.15,currentnumber*TotalLenght+5, 200).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
                $(".coverage").show()
                $(".dangqian-shijian").text("当前推进环数")
                $(".suowa-huanshu").text("推进环数："+currentnumber+"环")
                $(".suowa-lishu").text("推进里数："+currentnumber*2+"米")
            }).on('mouseout',function (e) {
            $("svg>line").remove()
            $(".coverage").hide()
        }).animate({duration: 1500, ease: '<>'}).fill('#00FC55').radius(7.5).animate({duration: 1500, ease: '<>'}).fill('#1493E4').radius(7).loop();
    }
    else if (currentnumber*TotalLenght>TotalLenght2&&currentnumber*TotalLenght<TotalLenght3){
        var sxy1=currentnumber*TotalLenght-TotalLenght3
        var svg2Height=((svg1Height*0.6-svg1Height*0.4)*sxy1)/(svg1Width*0.8-0.135*svg1Width)
        draw.polyline('0,'+svg1Height*0.15+' '+0.05*svg1Width+','+svg1Height*0.15+' '+0.135*svg1Width+','+svg1Height*0.4+' '+currentnumber*TotalLenght+','+(svg1Height*0.6+svg2Height)+'').fill('none').stroke({width: 5,color:'#1493e4'});
        draw.circle(14).move(currentnumber*TotalLenght,svg1Height*0.6+svg2Height-5).attr({
            'fill': '#1493E4',
            'stroke': '#fff',
            'stroke-width': 2
        }).off()
            .on('mousemove',function(e){
                $(".coverage").show()
                var e = event || window.event;
                $(".coverage").show().css({
                    "left":(e.screenX)*0.01+'rem',
                    "top":(e.screenY)*0.01+'rem'
                })
                draw.line(currentnumber*TotalLenght, svg1Height*0.15,currentnumber*TotalLenght, 200).stroke({width: 2,color:'rgb(0, 161, 87)'}).attr('stroke-dasharray',"5,5");
                $(".dangqian-shijian").text("当前推进环数")
                $(".suowa-huanshu").text("推进环数："+currentnumber+"环")
                $(".suowa-lishu").text("推进里数："+currentnumber*2+"米")

            }).on('mouseout',function (e) {
            $("svg>line").remove()
            $(".coverage").hide()
        }).animate({duration: 1500, ease: '<>'}).fill('#00FC55').radius(7.5).animate({duration: 1500, ease: '<>'}).fill('#1493E4').radius(7).loop();
    }else if (currentnumber*TotalLenght>TotalLenght3){
        var sxy1=currentnumber*TotalLenght-TotalLenght4
        var svg2Height=(svg1Height*0.6*sxy1)/(svg1Width-0.8*svg1Width)

        draw.polyline('0,'+svg1Height*0.15+' '+0.05*svg1Width+','+svg1Height*0.15+' '+0.135*svg1Width+','+svg1Height*0.4+' '+0.8*svg1Width+','+svg1Height*0.6+' '+currentnumber*TotalLenght+','+(-svg2Height-3)+'').fill('none').stroke({width: 5,color:'#1493e4'});
        draw.circle(14).move(currentnumber*TotalLenght,-svg2Height-10).attr({
            'fill': '#1493E4',
            'stroke': '#fff',
            'stroke-width': 2
        }).off()
            .on('mousemove',function(e){
                $(".coverage").show()
                var e = event || window.event;
                $(".coverage").show().css({
                    "left":(e.screenX)*0.01+'rem',
                    "top":(e.screenY)*0.01+'rem'
                })
                draw.line(currentnumber*TotalLenght+5, svg1Height*0.1,currentnumber*TotalLenght+5, 200).stroke({width: 2,color:'rgb(0, 161, 87)'}).attr('stroke-dasharray',"5,5");
                $(".dangqian-shijian").text("当前推进环数")
                $(".suowa-huanshu").text("推进环数："+currentnumber+"环")
                $(".suowa-lishu").text("推进里数："+currentnumber*2+"米")
            }).on('mouseout',function (e) {
            $("svg>line").remove()
            $(".coverage").hide()
        }).animate({duration: 1500, ease: '<>'}).fill('#00FC55').radius(7.5).animate({duration: 1500, ease: '<>'}).fill('#1493E4').radius(7).loop();
    }

    for (var  index=0;index<targetlist.length;index++){

        if(targetlist[index].targetring<currentnumber){
            var lineColor = 'lime';
            var flag = '../image/tbm/complete.png';
        }else {
           var lineColor = '#bfbfbf';
           var flag = '../image/tbm/uncomplete.png';
        }
        var targetGroup=draw.group()
        targetGroup.line(targetlist[index].targetring*TotalLenght, svg1Height*0.1,targetlist[index].targetring*TotalLenght,svg1Height).stroke({width: 2,color:lineColor}).attr('stroke-dasharray',"5,5");
        targetGroup.image(flag, 28, 28).move((targetlist[index].targetring*TotalLenght)-10, 0);
        targetGroup.text(function(add) {
            add.tspan(targetlist[index].startdate+'('+targetlist[index].enddate+')').newLine().fill(lineColor).font({size: '0.15rem'});
            add.tspan('推进环数: '+targetlist[index].targetring+' 环').newLine().fill(lineColor).font({size: '0.1rem'});
            add.tspan('推进里程: '+targetlist[index].targetmileage+' 米').newLine().fill(lineColor).font({size: '0.1rem'});
        }).attr({
            'x': targetlist[index].targetring*TotalLenght+10,
            'y': 0,
        });
    }



    for(let circlePoint in totaldatenumber){
      var sxy=  TotalLenght*(totaldatenumber[circlePoint])
        if (TotalLenght1>=sxy){
            draw.circle(15).move(sxy,svg1Height*0.1).attr({
                'fill': 'lime',
                'stroke': '#fff',
                'stroke-width': 2
            }).off()
                .on('mousemove',function(e){
                    var sxy=  TotalLenght*(totaldatenumber[circlePoint])
                    var e = event || window.event;
                    $(".coverage").show().css({
                        "left":(e.screenX)*0.01+'rem',
                        "top":(e.screenY)*0.01+'rem'
                    })
                    draw.line(sxy+5, svg1Height*0.15, sxy+5, 200).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
                    $(".dangqian-shijian").text(circlePoint)
                    $(".suowa-huanshu").text("推进环数："+totaldatenumber[circlePoint]+"环")
                    $(".suowa-lishu").text("推进里数："+totaldatenumber[circlePoint]*2+"米")

                }).on('mouseout',function (e) {
                $("svg>line").remove()
                    $(".coverage").hide().css({
                        "left":"",
                        "top":""
                    })
                })
        } else if (TotalLenght1<sxy && TotalLenght2>=sxy){
            var sxy1=sxy-TotalLenght1
        var svg2Height=((svg1Height*0.4-svg1Height*0.15)*sxy1)/(0.135*svg1Width-0.05*svg1Width)

            draw.circle(15).move(sxy,svg2Height+svg1Height*0.15).attr({
                'fill': 'lime',
                'stroke': '#fff',
                'stroke-width': 2
            }).off()
                .on('mousemove',function(e){

                    var sxy=  TotalLenght*(totaldatenumber[circlePoint])
                    var e = event || window.event;
                    draw.line(sxy+5, svg1Height*0.15, sxy+5, 200).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
                    $(".coverage").show().css({
                        "left":(e.screenX)*0.01+'rem',
                        "top":(e.screenY)*0.01+'rem'
                    })
                    $(".dangqian-shijian").text(circlePoint)
                    $(".suowa-huanshu").text("推进环数："+totaldatenumber[circlePoint]+"环")
                    $(".suowa-lishu").text("推进里数："+totaldatenumber[circlePoint]*2+"米")
                }).on('mouseout',function (e) {
                    $("svg>line").remove()
                $(".coverage").hide().css({
                    "left":"",
                    "top":""
                })
            })
        } else if (TotalLenght2<sxy && TotalLenght3>sxy){
            var sxy1=sxy-TotalLenght2
            var svg2Height=((svg1Height*0.6-svg1Height*0.4)*sxy1)/(0.8*svg1Width-0.135*svg1Width)
            draw.circle(15).move(sxy,svg1Height*0.4+svg2Height-5).attr({
                'fill': 'lime',
                'stroke': '#fff',
                'stroke-width': 2
            }).off()
                .on('mousemove',function(e){
                    var sxy=  TotalLenght*(totaldatenumber[circlePoint])
                    draw.line(sxy+5, svg1Height*0.15, sxy+5, 200).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
                    var e = event || window.event;
                    $(".coverage").show().css({
                        "left":(e.screenX)*0.01+'rem',
                        "top":(e.screenY)*0.01+'rem'
                    })
                    $(".dangqian-shijian").text(circlePoint)
                    $(".suowa-huanshu").text("推进环数："+totaldatenumber[circlePoint]+"环")
                    $(".suowa-lishu").text("推进里数："+totaldatenumber[circlePoint]*2+"米")
                }).on('mouseout',function (e) {
                $("svg>line").remove()
                $(".coverage").hide().css({
                    "left":"",
                    "top":""
                })
            })
        }else if (TotalLenght3<sxy && TotalLenght4>sxy){
            var sxy1=sxy-TotalLenght3
            var svg2Height=((svg1Height*0.6)*sxy1)/(svg1Width-0.8*svg1Width)

            draw.circle(15).move(sxy,svg1Height*0.6-svg2Height-10).attr({
                'fill': 'lime',
                'stroke': '#fff',
                'stroke-width': 2
            }).off()
                .on('mousemove',function(e){
                    var sxy=  TotalLenght*(totaldatenumber[circlePoint])
                    draw.line(sxy+5, svg1Height*0.15, sxy+5, 200).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
                    var e = event || window.event;
                    $(".coverage").show().css({
                        "left":(e.screenX)*0.01+'rem',
                        "top":(e.screenY)*0.01+'rem'
                    })
                    $(".dangqian-shijian").text(circlePoint)
                    $(".suowa-huanshu").text("推进环数："+totaldatenumber[circlePoint]+"环")
                    $(".suowa-lishu").text("推进里数："+totaldatenumber[circlePoint]*2+"米")
                }).on('mouseout',function (e) {
                $("svg>line").remove()
                $(".coverage").hide().css({
                    "left":"",
                    "top":""
                })
            })
        }

    }

}








tbmData.onmessage=function (event) {
    var socketData = JSON.parse(event.data);
    console.log(socketData.data[0])
    $("#container-name").empty()
    svgdraw(socketData.data[0].mysql.totalring,socketData.data[0].mysql.totaldatenumber,socketData.data[0].mysql.currentnumber,socketData.data[0].mysql.targetlist)
    var dataNo=socketData.data[0].es
    console.log(dataNo)
    $(".Groutin_systemnames").empty()
$(".slurry-inlet-left1").empty()
$(".slurry-inlet-left1").append("<div>左油箱<span>"+dataNo.njmzygyl+"</span>Bar</div>" +
    "<div>右油箱<span>"+dataNo.njmyygyl+"</span>Bar</div>" +
    "<div>左油箱<span>"+dataNo.njmyygyl+"</span>mm</div>" +
    "<div>右油箱<span>"+dataNo.njmyygyl+"</span>mm</div>")
    $(".slurry-inlet-left3").empty()
    $(".slurry-inlet-left3").append("<div>流量<span>"+dataNo.jjgljyll+"</span>m3/h</div>" +
        "<div>密度<span>"+dataNo.jjgljymdjc+"</span>kg/L</div>" +
        "<div>压力<span>"+dataNo.jjgljyll+"</span>Bar</div>")
    $(".slurry-inlet-left4").empty()
    $(".slurry-inlet-left4").append("<div>流量<span>"+dataNo.pjgljyll+"</span>m3/h</div>" +
        "<div>密度<span>"+dataNo.jjgljymdjc+"</span>kg/L</div>" )
    $(".slurry-inlet-left5").empty()
    $(".slurry-inlet-left5").append("<div>1#<span>"+dataNo.kwcyl1+"</span></div>" +
        "<div>2#<span>"+dataNo.kwcyl2+"</span></div>" +
        "<div>3#<span>"+dataNo.kwcyl3+"</span></div>" +
        "<div>4#<span>"+dataNo.kwcyl4+"</span></div>" +
        "<div>5#<span>"+dataNo.kwcyl5+"</span></div>")


$(".Water-run1-form1").empty()
    $(".Water-run1-form1").append("<div style='width: 100%; height: 0.2rem;  background: rgba(20,152,235,0.4);text-align: center;'>冲洗泵</div>" +
        "<div>" +
        "<span>设备</span>" +
        "<span>P0.1</span>" +
        "<span>P0.2</span>" +
        "</div>" +
        "<div>" +
        "<span>设置%</span>" +
        "<span>"+dataNo.cxbp01sdsz  +"</span>" +
        "<span>"+dataNo.cxbp01sdsz  +"</span>" +
        "</div>" +
        "<div>" +
        "<span>速度</span>" +
        "<span>"+dataNo.cxbsdp01+"</span>" +
        "<span>"+dataNo.cxbsdp02+"</span>" +
        "</div>" +
        "<div>" +
        "<span>电流A</span>" +
        "<span>"+dataNo.cxbdlp01+"</span>" +
        "<span>"+dataNo.cxbdlp02+"</span>" +
        "</div>" +
        "<div>" +
        "<span>压力Bar</span>" +
        "<span>"+dataNo.p01bckyl+"</span>" +
        "<span>"+dataNo.p02bckyl+"</span>" +
        "</div>" +
        "<div>" +
        "<span>流量m³/h</span>" +
        "<span>"+dataNo.p01bckll+"</span>" +
        "<span>"+dataNo.p02bckll +"</span>" +
        "</div>")
    $(".Water-run1-form2").empty()

    $(".Water-run1-form2").append("<div style='width: 100%; height: 0.2rem;  background: rgba(20,152,235,0.4);text-align: center;'>排浆泵</div>" +
        "<div>" +
        "<span>设备</span>" +
        "<span>P1.1</span>" +
        "<span>P1.2</span>" +
        "<span>P2.1</span>" +
        "<span>P2.2</span>" +
        "<span>p2.3</span>" +
        "</div>"+
        "<div>" +
         "<span>设置</span>" +
         "<span>"+dataNo.jjbp11sdsz+"</span>" +
         "<span>"+dataNo.jjbp12sdsz+"</span>" +
         "<span>"+dataNo.pjbp21sdsz+"</span>" +
        "<span>"+dataNo.pjbp22sdsz+"</span>" +
        "<span>"+dataNo.pjbp23sdsz+"</span>" +
         "</div>"+
         "<div>" +
         "<span>速度rnp</span>" +
         "<span>"+dataNo.cxbsdp11+"</span>" +
         "<span>"+dataNo.jjbsdp12+"</span>" +
         "<span>"+dataNo.pjbsdp21+"</span>" +
         "<span>"+dataNo.jjbsdp22+"</span>" +
        "<span>"+dataNo.pjbsdp23+"</span>" +
         "</div>" +
         "<div>" +
         "<span>电流A</span>" +
         "<span>"+dataNo.cxbdlp11+"</span>" +
         "<span>"+dataNo.jjbdlp12+"</span>" +
         "<span>"+dataNo.pjbdlp21+"</span>" +
         "<span>"+dataNo.jjbdlp22+"</span>" +
         "<span>"+dataNo.pjbdlp23+"</span>" +
         "</div>" +
         "<div>" +
         "<span>进口Bar</span>" +
         "<span>"+dataNo.jbjp11jnkyljc+"</span>" +
        "<span>"+dataNo.jjbp12jnkyljc+"</span>" +
         "<span>"+dataNo.pjbp21jnlyljc+"</span>" +
        "<span>"+dataNo.jjbjnkyljc+"</span>" +
        "<span>"+dataNo.pjbp23jnkyljc+"</span>" +
         "</div>" +
        "<div>" +
        "<span>出口Bar</span>" +
        "<span>"+dataNo.jbjp11cnkyljc+"</span>" +
        "<span>"+dataNo.jjbp12cnkyljc+"</span>" +
        "<span>"+dataNo.pjbp21cnkyljc+"</span>" +
        "<span>"+dataNo.jjbcnkyljc+"</span>" +
        "<span>"+dataNo.pjbp23cnkyljc+"</span>" +
        "</div>")
    $(".pressure-form").empty()
    $(".pressure-form").append("<tr>" +
        "<td class='pressure-form-top'></td>" +
        "<td>A1</td>" +
        "<td>A2</td>" +
        "<td>A3</td>" +
        "<td>A4</td>" +
        "<td>A5</td>" +
        "<td>A6</td>" +
        "<td>A7</td>" +
        "<td>A8</td>" +
        "</tr>" +
        "<tr>" +
        "<td>注浆口计数</td>" +
        "<td>"+dataNo.zjkjsq1+"</td>" +
        "<td>"+dataNo.zjkjsq2+"</td>" +
        "<td>"+dataNo.zjkjsq3+"</td>" +
        "<td>"+dataNo.zjkjsq4+"</td>" +
        "<td>"+dataNo.zjkjsq5+"</td>" +
        "<td>"+dataNo.zjkjsq6+"</td>" +
        "<td>"+dataNo.zjkjsq7+"</td>" +
        "<td>"+dataNo.zjkjsq8+"</td>" +
        "</tr>" +
        "<tr>" +
        "<td>注浆口压力</td>" +
        "<td>"+dataNo.sjzrk1yl+"</td>" +
        "<td>"+dataNo.sjzrk2yl+"</td>" +
        "<td>"+dataNo.sjzrk3yl+"</td>" +
        "<td>"+dataNo.sjzrk4yl+"</td>" +
        "<td>"+dataNo.sjzrk5yl+"</td>" +
        "<td>"+dataNo.sjzrk6yl+"</td>" +
        "<td>"+dataNo.sjzrk7yl+"</td>" +
        "<td>"+dataNo.sjzrk8yl+"</td>" +
        "</tr>")

    $(".Groutin_system21").append("<div>" +
        "<div>1#正转<span class='sjjbdjzz1'></span></div>" +
        "<div>1#反转<span class='sjjbdjfz1'></span></div>" +
        "</div>" +
        "<div>" +
        "<div>2#正转<span class='sjjbdjzz2'></span></div>" +
        "<div>2#反转<span class='sjjbdjfz2'></span></div>" +
        "</div>")
    $(".Groutin_system22").append("<div>" +
        "<div>3#正转<span class='sjjbdjzz3'></span></div>" +
        "<div>3#反转<span class='sjjbdjfz3'></span></div>" +
        "</div>" +
        "<div>" +
        "<div>4#正转<span class='sjjbdjzz4'></span></div>" +
        "<div>4#反转<span class='sjjbdjfz4'></span></div>" +
        "</div>")
    if(dataNo.sjjbdjzz1==true){
        $(".sjjbdjzz1").css("background","#00a157")
    }else {
        $(".sjjbdjzz1").css("background","#808080")
    }
    if(dataNo.sjjbdjfz1==true){
        $(".sjjbdjfz1").css("background","#00a157")
    }else {
        $(".sjjbdjfz1").css("background","#808080")
    }

    if(dataNo.sjjbdjzz2==true){
        $(".sjjbdjzz2").css("background","#00a157")
    }else {
        $(".sjjbdjzz2").css("background","#808080")
    }

    if(dataNo.sjjbdjfz2==true){
        $(".sjjbdjfz2").css("background","#00a157")
    }else {
        $(".sjjbdjfz2").css("background","#808080")
    }



    if(dataNo.sjjbdjzz3==true){
        $(".sjjbdjzz3").css("background","#00a157")
    }else {
        $(".sjjbdjzz3").css("background","#808080")
    }



    if(dataNo.sjjbdjfz3==true){
        $(".sjjbdjfz3").css("background","#00a157")
    }else {
        $(".sjjbdjfz3").css("background","#808080")
    }

    if(dataNo.sjjbdjzz4==true){
        $(".sjjbdjzz4").css("background","#00a157")
    }else {
        $(".sjjbdjzz4").css("background","#808080")
    }

    if(dataNo.sjjbdjfz4==true){
        $(".sjjbdjfz4").css("background","#00a157")
    }else {
        $(".sjjbdjfz4").css("background","#808080")
    }

    $(".Shield-cutter2").append("<div style='width: 100%; color: #008000; text-align: center; font-weight: bold; margin-top: 0.02rem;'>准备</div>" +
        "<div><div>刀盘转矩</div><span>"+dataNo.dpzz+"</span> kNm</div>" +
        "<div><div>刀盘转速</div><span>"+dataNo.dpzs+"</span> kNm</div>" +
        "<div><div>侧滚</div><span>"+dataNo.sbcg+"</span> mm</div>")
$(".Shield-cutter3").empty()
    $(".Shield-cutter3").append("<div>1# <span>"+dataNo.dwjxcl1+"</span>mm</div>" +
        "<div>2# <span>"+dataNo.dwjxcl2+"</span>mm</div>" +
        "<div>3# <span>"+dataNo.dwjxcl3+"</span>mm</div>" +
        "<div>4# <span>"+dataNo.dwjxcl4+"</span>mm</div>" +
        "<div>5# <span>"+dataNo.dwjxcl5+"</span>mm</div>")
    $(".Shield-Advance-cutter2").empty()
    $(".Shield-Advance-cutter2").append(" <div class='Shield-cutter1'>搅拌器</div>" +
        "<div style='display: flex; justify-content: space-around; color: #ffffff; margin-top: 0.1rem'>" +
        " <span>左</span>" +
        " <span>右</span>" +
        "</div>" +
        "<div class='jbjxz'>A:&nbsp;&nbsp;"+dataNo.zjbjxzsdz+"Bar &nbsp;&nbsp; "+dataNo.yjbjxzsdz+"Bar</div>" +
        "<div class='jbjxz'>B:&nbsp;&nbsp;"+dataNo.zjbjxzsd+"Bar &nbsp;&nbsp; "+dataNo.yjbjxzsd+"Bar </div>" +
        "<div class='jbjliuliang'>油脂次数 <span>"+dataNo.jbqrhyzjsq+"</span>次</div>" +
        "<div class='jbjliuliang'>HBW流量<span>"+dataNo.jbqmfhbwll+"</span>ml/min</div>" +
        "<div class='jbjliuliang'>液压油温度<span>"+dataNo.yyyxwd+"</span>℃</div>")

    $(".Azyg").empty();
    $(".Bzyg").empty();
    $(".Czyg").empty();
    $(".Dzyg").empty();
    $(".Ezyg").empty();
    $(".Fzyg").empty();
    $(".Azyg").append("<div>压力："+dataNo.azygtjyl+" Bar</div>" +
        "<div>行程："+dataNo.azugxc+"mm</div>" +
        "<div>推进量："+dataNo.ygtjl1+"mm</div>" +
        "<div>设置值："+dataNo.aqygylszz+"%</div>")
    $(".Bzyg").append("<div>压力："+dataNo.bzygtjyl+" Bar</div>" +
        "<div>行程："+dataNo.bzugxc+"mm</div>" +
        "<div>推进量："+dataNo.ygtjl2+"mm</div>" +
        "<div>设置值："+dataNo.bqygylszz+"%</div>")
    $(".Czyg").append("<div>压力："+dataNo.czygtjyl+" Bar</div>" +
        "<div>行程："+dataNo.czugxc+"mm</div>" +
        "<div>推进量："+dataNo.ygtjl3+"mm</div>" +
        "<div>设置值："+dataNo.cqygylszz+"%</div>")
    $(".Dzyg").append("<div>压力："+dataNo.dzygtjyl+" Bar</div>" +
        "<div>行程："+dataNo.dzugxc+"mm</div>" +
        "<div>推进量："+dataNo.ygtjl4+"mm</div>" +
        "<div>设置值："+dataNo.dqygylszz+"%</div>")
    $(".Ezyg").append("<div>压力："+dataNo.ezygtjyl+" Bar</div>" +
        "<div>行程："+dataNo.ezugxc+"mm</div>" +
        "<div>推进量："+dataNo.ygtjl5+"mm</div>" +
        "<div>设置值："+dataNo.eqygylszz+"%</div>")
    $(".Fzyg").append("<div>压力："+dataNo.fzygtjyl+" Bar</div>" +
        "<div>行程："+dataNo.fzugxc+"mm</div>" +
        "<div>推进量："+dataNo.ygtjl6+"mm</div>" +
        "<div>设置值："+dataNo.fqygylszz+"%</div>")
$(".Tunjin_xitong").empty()
    $(".Tunjin_xitong").append("<div>压力：Bar</div>" +
        "<div>推力："+dataNo.ztjl+"BN</div>" +
        "<div>速度："+dataNo.tjsdpjz+"mm/min</div>" +
        "<div>理论："+dataNo.tjlljssd+"%</div>" +
        "<div>贯入度："+dataNo.grd+"mm/r</div>" +
        "<div>倾角："+dataNo.sbqj+"%</div>")
$(".Tuili_zhongxin").empty()
    $(".Tuili_zhongxin").append("<div style='color: #ffffff; text-align: center;'>X:"+dataNo.tlzxx+" Y:"+dataNo.tlzzy+"</div>")
    $(".mifengrunhua").empty()
    $(".mifengrunhua").append("<div><span>主轴承第二道外密封1</span><span class='zzcdedwmfyzmc1zc'></span></div>" +
        "<div><span>主轴承第二道外密封2</span><span class='zzcdedwmfyzmc2zc'></span></div>" +
        "<div><span>主轴承第二道外密封3</span><span class='zzcdedwmfyzmc3zc'></span></div>" +
        "<div><span>主轴承第三道外密封1</span><span class='zzcdsdwmfyzmc1zc'></span></div>" +
        "<div><span>主轴承第三道外密封2</span><span class='zzcdsdwmfyzmc2zc'></span></div>" +
        "<div><span>盾尾密封</span><span class='dwmfzc'></span></div>" +
        "<div><span>齿轮油润滑</span><span></span></div>" +
        "<div><span>拼装机转子润滑</span><span class='pzjzzrhzc'></span></div>" +
        "<div><span>1#HBW油脂</span><span class='hbwyzzc1'></span></div>" +
        "<div><span>2#HBW油脂</span><span class='hbwyzzc2'></span></div>" +
        "<div><span>主驱动HBW内密封</span><span class='zqdhbwnmfzc'></span></div>" +
        "<div><span>1#空压机运行</span><span class='kyjyx1'></span></div>" +
        "<div><span>2#空压机运行</span><span class='kyjyx2'></span></div>" +
        "<div><span>3#空压机运行</span><span class='kyjyx3'></span></div>" +
        "<div><span>4#空压机运行</span><span class='kyjyx4'></span></div>" +
        "<div><span>1#冷干机运行</span><span class='lgjyx1'></span></div>" +
        "<div><span>1#冷干机运行</span><span class='lgjyx2'></span></div>")
    if (dataNo.zzcdedwmfyzmc1zc==true){
        $(".zzcdedwmfyzmc1zc").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".zzcdedwmfyzmc1zc").text("异常")
    }

    if (dataNo.zzcdedwmfyzmc2zc==true){
        $(".zzcdedwmfyzmc2zc").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".zzcdedwmfyzmc2zc").text("异常")
    }
    if (dataNo.zzcdedwmfyzmc3zc==true){
        $(".zzcdedwmfyzmc3zc").text("正常").css("color","rgb(0, 161, 87")
    } else {
        $(".zzcdedwmfyzmc3zc").text("异常")
    }
    if (dataNo.zzcdsdwmfyzmc1zc==true){
        $(".zzcdsdwmfyzmc1zc").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".zzcdsdwmfyzmc1zc").text("异常")
    }
    if (dataNo.zzcdsdwmfyzmc2zc==true){
        $(".zzcdsdwmfyzmc2zc").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".zzcdsdwmfyzmc2zc").text("异常")
    }




    if (dataNo.dwmfzc==true){
        $(".dwmfzc").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".dwmfzc").text("异常")
    }
    if (dataNo.pzjzzrhzc==true){
        $(".pzjzzrhzc").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".pzjzzrhzc").text("异常")
    }

    if (dataNo.hbwyzzc1==true){
        $(".hbwyzzc1").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".hbwyzzc1").text("异常")
    }
    if (dataNo.hbwyzzc2==true){
        $(".hbwyzzc2").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".hbwyzzc2").text("异常")
    }



    if (dataNo.zqdhbwnmfzc==true){
        $(".zqdhbwnmfzc").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".zqdhbwnmfzc").text("异常")
    }
    if (dataNo.kyjyx1==true){
        $(".kyjyx1").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".kyjyx1").text("异常")
    }

    if (dataNo.kyjyx2==true){
        $(".kyjyx2").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".kyjyx2").text("异常")
    }
    if (dataNo.kyjyx3==true){
        $(".kyjyx3").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".kyjyx3").text("异常")
    }
    if (dataNo.kyjyx4==true){
        $(".kyjyx4").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".kyjyx4").text("异常")
    }


    if (dataNo.lgjyx1==true){
        $(".lgjyx1").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".lgjyx1").text("异常")
    }
    if (dataNo.lgjyx2==true){
        $(".lgjyx2").text("正常").css("color","rgb(0, 161, 87)")
    } else {
        $(".lgjyx2").text("异常")
    }
    $(".cutterhead-name1").empty()
    $(".cutterhead-name1").append("<div style='color: #00a65a; text-align: center;'>准备</div>" +
        "<div>转矩<span>"+dataNo.dpzz+"</span>kNm</div>" +
        "<div>速度<span>"+dataNo.dpzs+"</span>rpm</div>" +
        "<div>侧滚<span>"+dataNo.sbcg+"</span>mm</div>" +
        "")
    $(".shuju div").empty()
    $(".shuju1").append("<div>" +
        "<div></div>" +
        "<div>1#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj1sd+"rpm</div>" +
        "<div>"+dataNo.zqddj1nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj1dl+"A</div>" +
        "<div>"+dataNo.zqddj1wd+"℃</div>" +
        "</div>")
    $(".shuju2").append("<div>" +
        "<div></div>" +
        "<div>2#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj2sd+"rpm</div>" +
        "<div>"+dataNo.zqddj2nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj2dl+"A</div>" +
        "<div>"+dataNo.zqddj2wd+"℃</div>" +
        "</div>")
    $(".shuju3").append("<div>" +
        "<div></div>" +
        "<div>3#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj3sd+"rpm</div>" +
        "<div>"+dataNo.zqddj3nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj3dl+"A</div>" +
        "<div>"+dataNo.zqddj3wd+"℃</div>" +
        "</div>")
    $(".shuju4").append("<div>" +
        "<div></div>" +
        "<div>4#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj4sd+"rpm</div>" +
        "<div>"+dataNo.zqddj4nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj4dl+"A</div>" +
        "<div>"+dataNo.zqddj4wd+"℃</div>" +
        "</div>")
    $(".shuju5").append("<div>" +
        "<div></div>" +
        "<div>5#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj5sd+"rpm</div>" +
        "<div>"+dataNo.zqddj5nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj5dl+"A</div>" +
        "<div>"+dataNo.zqddj5wd+"℃</div>" +
        "</div>")
    $(".shuju6").append("<div>" +
        "<div></div>" +
        "<div>6#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj6sd+"rpm</div>" +
        "<div>"+dataNo.zqddj6nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj6dl+"A</div>" +
        "<div>"+dataNo.zqddj6wd+"℃</div>" +
        "</div>")
    $(".shuju7").append("<div>" +
        "<div></div>" +
        "<div>7#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj7sd+"rpm</div>" +
        "<div>"+dataNo.zqddj7nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj7dl+"A</div>" +
        "<div>"+dataNo.zqddj7wd+"℃</div>" +
        "</div>")
    $(".shuju8").append("<div>" +
        "<div></div>" +
        "<div>8#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj8sd+"rpm</div>" +
        "<div>"+dataNo.zqddj8nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj8dl+"A</div>" +
        "<div>"+dataNo.zqddj8wd+"℃</div>" +
        "</div>")
    $(".shuju9").append("<div>" +
        "<div></div>" +
        "<div>9#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj9sd+"rpm</div>" +
        "<div>"+dataNo.zqddj9nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj9dl+"A</div>" +
        "<div>"+dataNo.zqddj9wd+"℃</div>" +
        "</div>")
    $(".shuju10").append("<div>" +
        "<div></div>" +
        "<div>10#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj10sd+"rpm</div>" +
        "<div>"+dataNo.zqddj10nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj10dl+"A</div>" +
        "<div>"+dataNo.zqddj10wd+"℃</div>" +
        "</div>")
    $(".shuju11").append("<div>" +
        "<div></div>" +
        "<div>11#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj11sd+"rpm</div>" +
        "<div>"+dataNo.zqddj11nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj11dl+"A</div>" +
        "<div>"+dataNo.zqddj11wd+"℃</div>" +
        "</div>")
    $(".shuju12").append("<div>" +
        "<div></div>" +
        "<div>12#刀盘</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj12sd+"rpm</div>" +
        "<div>"+dataNo.zqddj12nj+"Nm</div>" +
        "</div>" +
        "<div>" +
        "<div>"+dataNo.zqddj12dl+"A</div>" +
        "<div>"+dataNo.zqddj12wd+"℃</div>" +
        "</div>")
    $(".cylindrical-plot-center1").text(dataNo.yyyxwd+"℃")
    $(".cylindrical-plot-center2").text(dataNo.clyxwd1+"℃")
    $(".cylindrical-plot-center3").text(dataNo.clyxwd2+"℃")
    $(".center-plot1").height(dataNo.yyyxwd+"%")
    $(".center-plot2").height(dataNo.clyxwd1+"%")
    $(".center-plot3").height(dataNo.clyxwd2+"%")
    $(".daopianxuke").text(dataNo.dpyxxkztz)
    $(".Advance_permission").text(dataNo.tjyxxkztz)
    $(".DPSLXT").empty()
    $(".DPSLXT").append("<div><div>变频水冷系统内循环水温度(C)"+dataNo.bpslxtnxhswd+"</div><div>变频水冷系统内循环出水温度(C)"+dataNo.bpslxtnxhjswd+"</div></div>" +
        "<div><div>变频水冷系统内循环水进水压力(bar)"+dataNo.bpslxtnxhsjsyl+"</div><div>变频水冷系统内循环水进水压力(bar)"+dataNo.bpslxtnxhjsyl+"</div></div>" +
        "<div><div>变频水冷系统内循环出水流量(L/min)"+dataNo.bpslxtnxhcsyl+"</div><div>变频水冷系统冷源水进水温度(C)"+dataNo.bpslxtlysjswd+"</div></div>")

}




