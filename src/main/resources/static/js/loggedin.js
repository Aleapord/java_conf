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
                "                    <button class=\"material-button text-center\" ><a href='exportExcel?confid="+data.confid+"'>导出为excel</a></button>\n" +
                "<div class='container text-center'> " +
                "                       <h3  class='text-center'>报名此会议需要填写的信息</h3><br>" +
                "                                                               " +
                "     " +(data.confneed.match("name")?"姓名：<input type='text' id=\"needname\"> <br>":"")+
                "" +(data.confneed.match("until")?"单位：<input type='text' id=\"needuntil\"> <br>":"")+

                "" +(data.confneed.match("id_card")?"身份证号：<input type='text' id=\"needid_card\"> <br>":"")+
                "" +(data.confneed.match("tel")?"电话：<input type='text' id=\"needtel\"> <br>":"")+
                "" +(data.confneed.match("date")?"日期：<input type='text' id=\"needdate\"> <br>":"")+
                "" +(data.confneed.match("sex")?"性别：<input type='text' id=\"needsex\"> <br>":"")+
                "" +(data.confneed.match("room")?"是否安排房间：<input type='text' id=\"needroom\"> <br>":"")+
                "" +
                "" + " " +
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

