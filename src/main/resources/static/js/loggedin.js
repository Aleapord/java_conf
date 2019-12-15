$(function () {
    var user = sessionStorage.getItem("user");
    if(user!==null){
        $("#username").text(user);

        load_conf_list();
    }
    else if(user==="Admin"){
        window.open("/Admin")
    }
    else {
        document.body.innerHTML="<h1 class='text-warning text-center'>请登录后再访问此页面</h1>"
    }
});
function load_conf_list(){
    $("#navigation").html("<ul class=\"navbar-nav flex-column text-left\">\n" +
        "\t\t\t\t\t<li class=\"nav-item active\" id=\"conf_list_nav\">\n" +
        "\t\t\t\t\t    <a class=\"nav-link\" onclick=\"load_conf_list()\"><i class=\"fas fa-list fa-fw mr-2\"></i>会议列表 <span class=\"sr-only\">(current)</span></a>\n" +
        "\t\t\t\t\t</li>\n" +
        "\t\t\t\t\t<li class=\"nav-item\">\n" +
        "\t\t\t\t\t    <a class=\"nav-link\" onclick=\"show_user_inf()\"><i class=\"fas fa-user fa-fw mr-2\" ></i>我报名的会议</a>\n" +
        "\t\t\t\t\t</li>\n" +
        "\t\t\t\t</ul>\n" +
        "\t\t\t\t\n" +
        "\t\t\t\t<div class=\"my-2 my-md-3\">\n" +
        "\t\t\t\t    <a class=\"btn btn-primary\" href=\"/\" onclick=\"logout()\">退出登陆</a>");
    $("#title").text("会议列表");
    $.ajax({
        url:"getAllConf/",
        success:function (data) {
            $("#text_cont").html('');
            addlist(data);
        }
    })
}
function load_conf_detail(confid) {
    var container =  $("#text_cont");
    container.text("");
    var title='123';
    $.ajax({
        url:'getConf/',
        data:{
            confid:confid
        },
        success:function (data) {
            console.log(data.confneed);
            $("#title").text(data.confname);
            $("#text_cont").append("<table class=\"table-bordered col-10 \">\n" +
                "                       <tr>\n" +
                "                           <td colspan=\"2\">\n" + data.confinf+
                "                           </td>\n" +
                "                       </tr>\n" +
                "                       <tr>\n" +
                "                           <td>会议地点：</td>\n" +
                "                           <td>"+data.confplace+"</td>\n" +
                "                       </tr>\n" +
                "                       <tr>\n" +
                "                           <td>会议宾馆：</td>\n" +
                "                           <td>"+ data.confhotle+"</td>\n" +
                "                       </tr>\n" +
                "                       <tr>\n" +
                "                           <td>会议时间：</td>\n" +
                "                           <td>"+data.conftime+"</td>\n" +
                "                       </tr>\n" +
                "                       <tr>\n" +
                "                           <td>会议人物：\n" +
                "                           </td>\n" +
                "                           <td>\n" +
                "                               "+data.confimg+"\n" +
                "                           </td>\n" +
                "                       </tr>\n" +
                "\n" +
                "                   </table>\n" +
                "                    <button class=\"material-button float-left\" onclick='exportExcel("+ data.confid+")'>导出为excel</button>\n" +
                "<button class='material-button offset-7' onclick='erweima("+data.confid+")'>生成二维码</button> " +
                "<div class='container text-center'> " +
                "                       <h3  class='text-center'>报名此会议需要填写的信息</h3><br>" +
                "                                                          <table style='margin: 0 auto;'>     " +
                "     " +(data.confneed.match("name")?"<tr><td>姓名：</td><td><input type='text' id='needname'></td></tr>":"")+
                "" +(data.confneed.match("until")?"<tr><td>单位：</td><td><input type='tel' id='needuntil'> </td></tr>":"")+

                "" +(data.confneed.match("id_card")?"<tr><td>身份证号：</td><td><input type='text' id='needid_card'> </td>":"")+
                "" +(data.confneed.match("tel")?"<tr><td>电话：</td><td><input type='text' id='needtel'></td></tr>":"")+
                "" +(data.confneed.match("date")?"<tr><td>日期：</td><td><input type='date' id='needdate'></td></tr>":"")+
                "" +(data.confneed.match("sex")?"<tr><td>性别：</td><td><select  id='needsex'><option value='男'>男</option><option value='女'>女</option></select> </td></tr>":"")+
                "" +(data.confneed.match("room")?"<tr><td>是否安排房间：</td><td><select  id='needroom'><option value='是'>是</option><option value='否'>否</option></select></td></tr>":"")+
                "" +
                "" + " </table>" +
                "" +
                "" +
                "" +
                " <button class=\"material-button text-center\" onclick=\"baoming("+data.confid+")\">报名此会议</button>\n" +
                "                        " +
                "</div>" +
                "" +
                "" +
                "" +
                "" +
                "\n")
        }
    });


}

function exportExcel(confid) {
    window.open("exportExcel?confid="+confid)
}

function baoming(confid) {
    var attr = {
        'name':sessionStorage.getItem('user'),
        'userid':sessionStorage.getItem('userid'),
        'confid':confid,
        'until':$("#needuntil").val(),
        'id_card':$("#needid_card").val(),
        'tel':$("#needtel").val(),
        'time':$("#needdate").val(),
        'sex':$("#needsex").val(),
        'room':$("#needroom").val()
    };

    var data=JSON.stringify(attr);
    $.post({
        url:"baoming/",
        data:data,
        contentType:'application/json',
        success:function (data) {
            alert(data.msg);
        }
    })

}

function erweima(confid) {

    window.open("qrcode/?id="+confid);

}

function loadform() {
    $("#text_cont").html(" <form>\n" +
        "                    <label for=\"name\">姓名：</label><input type=\"text\"  id=\"name\" class=\"offset-1\"><br>\n" +
        "                    <label for=\"untils\">工作单位：</label><input type=\"text\" class=\"offset-1\" id=\"untils\"><br>\n" +
        "                    <label for=\"id_card\">身份证号：</label><input type=\"text\" class=\"offset-1\" id=\"id_card\"><br>\n" +
        "                    <label for=\"phone\">电话：</label><input type=\"tel\" class=\"offset-1\" id=\"phone\"><br>\n" +
        "                    <label for=\"time\">参会时间：</label><input type=\"date\" class=\"offset-1\" id=\"time\"><br>\n" +
        "                    <label for=\"sex\">性别：</label><select  class=\"offset-1\" id=\"sex\">\n" +
        "                    <option value=\"0\">男</option>\n" +
        "                    <option value=\"1\">女</option></select><br>\n" +
        "                    <label for=\"room\">是否安排房间：</label><input type=\"checkbox\" class=\"offset-1\" id=\"room\"><br>\n" +
        "                </form>\n" +
        "                \n" +
        "                \n" +
        "                    <button class=\"material-button text-center\">报名此会议</button>")

}

function addlist(data) {
    $.each(data,function (idx,obj) {
        $("#text_cont").append("<div class=\"item mb-5\">\n" +
            "\t\t\t\t    <div class=\"media\">\n" +
            "\t\t\t\t\t    <img class=\"mr-3 img-fluid post-thumb d-none d-md-flex\" src=\"/images/"+obj.confimg+"\" alt=\"image\">\n" +
            "\t\t\t\t\t    <div class=\"media-body\">\n" +
            "\t\t\t\t\t\t    <h3 class=\"title mb-1\">"+obj.confname+"</h3>\n" +
            "\t\t\t\t\t\t    <div class=\"meta mb-1\"><span class=\"date\">发布于： "+obj.conftime+
            "\t\t\t\t\t\t    <div class=\"intro\">"+obj.confinf+"</div>\n" +
            "\t\t\t\t\t\t    <button class=\"more-link\" onclick='load_conf_detail("+obj.confid+")'><b>去报名</b> &rarr;</button>\n" +
            "\t\t\t\t\t    </div><!--//media-body-->\n" +
            "\t\t\t\t    </div><!--//media-->\n" +
            "\t\t\t    </div><!--//item-->" );
    })
}

function logout() {
    sessionStorage.removeItem("user")


}
function show_user_inf() {
    $.post({
        url:'getMyConfs',
        data:{
            "userid":sessionStorage.getItem("userid")
        },
        success:function (data) {
            $("#navigation").html("<ul class=\"navbar-nav flex-column text-left\">\n" +
                "\t\t\t\t\t<li class=\"nav-item\" id=\"conf_list_nav\">\n" +
                "\t\t\t\t\t    <a class=\"nav-link\" onclick=\"load_conf_list()\"><i class=\"fas fa-list fa-fw mr-2\"></i>会议列表 <span class=\"sr-only\">(current)</span></a>\n" +
                "\t\t\t\t\t</li>\n" +
                "\t\t\t\t\t<li class=\"nav-item active\">\n" +
                "\t\t\t\t\t    <a class=\"nav-link\" onclick=\"show_user_inf()\"><i class=\"fas fa-user fa-fw mr-2\" ></i>我报名的会议</a>\n" +
                "\t\t\t\t\t</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t<div class=\"my-2 my-md-3\">\n" +
                "\t\t\t\t    <a class=\"btn btn-primary\" href=\"/\" onclick=\"logout()\">退出登陆</a>");
            $("#title").text("我报名的会议");
           $("#text_cont").html("" +
               "" +
               "<div class='offset-2'>" +
               "" +
               "" +
               "<table class='offset-2' id='myconftable' border='1'>" +
               "" +
               "</table>" +
               "" +
               "" +
               "</div>" +
               "" +
               "" +
               "" +
               "" +
               "" +
               "" +
               "");
            $.each(data,function (idx, obj) {
                $("#myconftable").append("" +
                    "<tr>" +
                    "<td>"+obj.confname+"</td>" +
                    "<td><a href='#' onclick='tuixuan("+obj.confid+")'>退选</a></td>" +
                    "" +
                    "</tr>" +
                    "" +
                    "" +
                    "")
            })
        }
    })
}
function tuixuan(confid) {
    var attr={
        'confid':confid,
        'userid':sessionStorage.getItem("userid")
    };
    var data=JSON.stringify(attr);

    $.post({
        url:"tuixuan/",
        contentType:'application/json',
        data:data,
        success:function (data) {
            alert(data.msg);
            history.go(0);
        }
    })



}
