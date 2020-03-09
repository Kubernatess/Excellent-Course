<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>index</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
<script src="js/search.js"></script>
</head>

<body>
<!-- 导航栏部分 -->
<jsp:include page="nav.jsp"/>

<!-- 侧边栏部分 -->
<aside>
<span></span><span>精品课程</span>
<ol>
<li>软件定义网络</li>
<li>计算机网络</li>
<li>OpenStack云计算</li>
<li>OpenGL图像编程</li>
<li>编译原理</li>
<li>Zbrush——影视造型</li>
<li>Unity3D游戏开发</li>
<li>Android应用开发</li>
<li>SPSS统计分析</li>
<li>Python数据分析</li>
</ol>
</aside>

<!-- 定义一个大容器 -->
<div class="container">
<!-- 分类部分 -->
<div class="category">
<ul>
<li><a>软件系</a></li><li><a href="content.jsp?major=电子与计算机工程" target="iframeA">电子与计算机工程</a></li><li><a href="content.jsp?major=数据科学与大数据技术" target="iframeA">数据科学与大数据技术</a></li><li><a href="content.jsp?major=软件工程" target="iframeA">软件工程</a></li>
</ul>
<ul>
<li><a>网络系</a></li><li><a href="content.jsp?major=网络工程" target="iframeA">网络工程</a></li><li><a href="content.jsp?major=信息管理与信息系统" target="iframeA">信息管理与信息系统</a></li>
</ul>
<ul>
<li><a>电子系</a></li><li><a href="content.jsp?major=智能科学与技术" target="iframeA">智能科学与技术</a></li><li><a href="content.jsp?major=电子信息工程" target="iframeA">电子信息工程</a></li><li><a href="content.jsp?major=通信工程" target="iframeA">通信工程</a></li><li><a href="content.jsp?major=自动化" target="iframeA">自动化</a></li>
</ul>
<ul>
<li><a>游戏系</a></li><li><a href="content.jsp?major=数字媒体技术" target="iframeA">数字媒体技术</a></li><li><a href="content.jsp?major=动画" target="iframeA">动画</a></li><li><a href="content.jsp?major=网络与新媒体" target="iframeA">网络与新媒体</a></li>
</ul>
<ul>
<li><a>数码系</a></li><li><a href="content.jsp?major=环境设计" target="iframeA">环境设计</a></li><li><a href="content.jsp?major=风景园林" target="iframeA">风景园林</a></li><li><a href="content.jsp?major=视觉传达设计" target="iframeA">视觉传达设计</a></li><li><a href="content.jsp?major=数字媒体艺术" target="iframeA">数字媒体艺术</a></li><li><a href="content.jsp?major=产品设计" target="iframeA">产品设计</a></li>
</ul>
<ul>
<li><a>管理系</a></li><li><a href="content.jsp?major=行政管理" target="iframeA">行政管理</a></li><li><a href="content.jsp?major=物流管理" target="iframeA">物流管理</a></li><li><a href="content.jsp?major=工商管理" target="iframeA">工商管理</a></li><li><a href="content.jsp?major=市场营销" target="iframeA">市场营销</a></li><li><a href="content.jsp?major=人力资源管理" target="iframeA">人力资源管理</a></li>
</ul>
<ul>
<li><a>国贸系</a></li><li><a href="content.jsp?major=电子商务" target="iframeA">电子商务</a></li><li><a href="content.jsp?major=国际经济与贸易" target="iframeA">国际经济与贸易</a></li>
</ul>
<ul>
<li><a>财会系</a></li><li><a href="content.jsp?major=财务管理" target="iframeA">财务管理</a></li><li><a href="content.jsp?major=会计学" target="iframeA">会计学</a></li>
</ul>
<ul>
<li><a>外语系</a></li><li><a href="content.jsp?major=英语" target="iframeA">英语</a></li><li><a href="content.jsp?major=日语" target="iframeA">日语</a></li>
</ul>
<ul>
<li><a>计算机系</a></li><li><a href="content.jsp?major=计算机科学与技术" target="iframeA">计算机科学与技术</a></li><li><a href="content.jsp?major=物联网工程" target="iframeA">物联网工程</a></li>
</ul>
</div>

<!-- 选项卡部分 -->
<span class="tab">
<a>热门课程</a><a>推荐课程</a><a>最新课程</a>
</span>

<!-- 搜索框部分 -->
<form>
<input type="search" placeholder="搜索感兴趣的课程" /><input type="image" src="image/search.png" />
</form>

<iframe src="content.jsp" scrolling="no" height="650" name="iframeA">
	<p>您的浏览器不支持  iframe 标签。</p>
</iframe>

</div>


<!-- 分页部分 -->
<div class="page">
<ul>
<li><a href="#">«</a></li><li><a href="#">1</a></li><li><a class="active" href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">...</a></li><li><a href="#">30</a></li><li><a href="#">»</a></li>
</ul>
</div>

<!-- 页脚部分 -->
<footer>
<p>版权所有：广州大学华软软件学院</p>
<p>Copyright 🌏 2019 Company name All rights reserved.</p>
</footer>

</body>
</html>
