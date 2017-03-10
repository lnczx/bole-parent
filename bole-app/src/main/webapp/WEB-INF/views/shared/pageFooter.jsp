
<!--common script for all pages-->
<script src="http://libs.cdnjs.net/jquery/2.1.4/jquery.min.js"></script>
<script>
if (!window.jQuery) {
var script = document.createElement('script');
script.src = "/static/js/jquery-2.1.4.min.js";
document.body.appendChild(script);
}
</script>

<script type="text/javascript" src="<c:url value='/static/js/lib/fastclick.js'/>"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="../js/jquery-weui.js"></script>
<script type="text/javascript" src="<c:url value='/static/js/lib/jquery-weui.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/static/js/bole/common-scripts.js'/>"></script>


