<!DOCTYPE html>
<html>
<body>

<script>
  alert("aaa");
  function loadDoc() {
    alert("bbb");
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        document.getElementById("demo").innerHTML =
                this.responseText;
      }
    };
    xhttp.open("GET", "ajax_info.txt", true);
    xhttp.send();
  }
</script>

<div id="demo">
  <h1>XMLHttpRequest 对象</h1>
  <button type="button" onclick="loadDoc()">修改内容</button>
</div>

</body>
</html>