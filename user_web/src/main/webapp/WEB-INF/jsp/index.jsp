<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<input name="realPicFile3" id="realPicFile3" type="file" onchange="ajaxFileUpload('realPicFile3','realPic3','hiddenImgValue3')" />
<input type=button onclick="upImg('realPicFile3')" value="上传" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    function upImg(fileID){
        $("#"+fileID).click();
    }
    function ajaxFileUpload(fileID,imgID,hiddenImgValue) {
        /*var file = $("#"+hiddenImgValue).val();
        if(!/\.(jpg|png|JPG|PNG)$/.test(file)){
            Error("不支持的图片格式.图片类型必须是.jpg,png格式.");
            return false;
        }*/
        $.ajaxFileUpload({
            url : '/img/upLoadImg?inputId='+fileID,
            secureuri : false,
            fileElementId : fileID,
            dataType : 'json',
            success : function(data, status){
                $("#"+imgID).attr("src", data.result);
                $("#"+hiddenImgValue).val(data.result);
                //$(this).val(data.result);
                //console.log($('#realPicFile'));
            },
            error : function(data, status, e){
                alert(e);
                //Error(e);
            }
        });
        return true;
    }
</script>
</body>
</html>
