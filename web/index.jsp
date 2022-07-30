<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <script>
    alert();
    $(function() {
      alert("111");
      //监听input输入的变化
      $("#name").on("input propertychange",function () {
        let div = $("#tips");

        if (this.value == "") {
          //如果没有输入数据则将下拉框隐藏
          div.css("display", "none");
        }
        $.ajax({
          url:"/search" , //请求路径
          type:"get" , //请求方式
          data:{"name":this.value},//请求参数
          dataType:"text",//设置接受到的响应数据的格式
          //回调函数
          success:function (data) {
            if (data == "") {
              return;
            }
            let childDiv = "";

            //按照，分割字符串
            let result = data.split(",");
            //遍历将html标签拼接放到div中
            for (r of result) {
              childDiv += "<div onclick='writeText(this)' onmouseover='changeBackground_over(this)' onmouseout='changeBackground_out(this)'>"+ r +"</div>";
            }

            //将拼接好的数据放到div中
            div.html(childDiv);
            //将div展示
            div.css("display","block");
          },
          error:function (aa) {
            console.log("出错啦...")
          }//表示如果请求响应出现错误，会执行的这个回调函数

        });
      });
    });

    //鼠标悬浮时，改变背景色
    function changeBackground_over(div){
      div.style.backgroundColor = "orange";
    }
    //鼠标离开时，恢复背景色
    function changeBackground_out(div){
      div.style.backgroundColor = "";
    }

    //将文本填充到搜索框
    function writeText(div){
      //得到搜索框
      let searchElement = document.getElementById("name");
      searchElement.value = div.innerHTML;//把div中的文本添加到搜索框中
      div.parentNode.style.display="none";//把context1的div隐藏
    }
  </script>
  <body>
  111111111
  <!-- 用户输入框 -->
  <div id="divsearch">
    <input type="text" name="name" id="name" />
    <input type="submit" value="搜索" >

  </div>
  <!-- 下拉提示框 -->
  <div id="tips"
       style="display: none; border: 1px solid red; background-color: white; width: 128px; top: 135px;">

  </div>

  </body>
</html>
