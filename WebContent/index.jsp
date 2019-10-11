<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>index</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/content.css" rel="stylesheet" type="text/css">
</head>

<body>
<!-- 导航栏部分 -->
<nav>
<img src="image/logo.png">
<span>广州大学华软软件学院</span>
<a href="login.jsp" target="_blank"><img src="image/user-empty.png"> 登陆</a>
</nav>

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
<div id="container">
<!-- 分类部分 -->
<div id="category">
<div><a>软件系</a><span>&gt;</span><a>数据科学与大数据技术</a><a>软件工程</a><a>软件工程(国际版)</a></div>
<div><a>网络系</a><span>&gt;</span><a>网络工程</a><a>网络工程(华为创新实验班)</a><a>信息管理与信息系统</a></div>
<div><a>电子系</a><span>&gt;</span><a>智能科学与技术(人工智能创新实验班)</a><a>自动化</a><a>电子信息工程</a><a>通信工程</a></div>
<div><a>游戏系</a><span>&gt;</span><a>数字媒体技术</a><a>动画</a><a>网络与新媒体</a></div>
<div><a>数码系</a><span>&gt;</span><a>环境设计</a><a>风景园林</a><a>视觉传达设计</a><a>数字媒体艺术</a><a>产品设计</a></div>
<div><a>管理系</a><span>&gt;</span><a>行政管理</a><a>物流管理</a><a>工商管理</a><a>市场营销</a><a>人力资源管理</a></div>
<div><a>国贸系</a><span>&gt;</span><a>电子商务</a><a>国际经济与贸易</a></div>
<div><a>财会系</a><span>&gt;</span><a>财务管理</a><a>会计学</a></div>
<div><a>外语系</a><span>&gt;</span><a>英语</a><a>日语</a></div>
<div><a>计算机系</a><span>&gt;</span><a>计算机科学与技术</a><a>物联网工程</a></div>
</div>

<!-- 选项卡部分 -->
<span id="tab">
<a>热门课程</a><a>推荐课程</a><a>最新课程</a>
</span>

<!-- 搜索框部分 -->
<form>
<input type="search" placeholder="搜索感兴趣的课程" /><input type="button" />
</form>
<hr>

<!-- 内容显示部分 -->
<div class="content">
<img src="image/cover.jpg">
<p>C语言程序设计</p>
<span>计算机系</span><span>蔡木生、王建红、卢坤菲、梁洪宜、高巍巍、冯华、李鹏、张艳玲</span>
<p>C语言是计算机程序设计的主流语言之一，经过40年的不断发展、完善，已成为国内外公认的一种优秀程序设计语言，有着其它语言不可比拟的...</p>
<img src="image/user-empty.png" /><span>225</span><img src="image/chat.png"  /><span>28</span>
</div>

<div class="content">
<img src="image/cover.jpg">
<p>C语言程序设计</p>
<span>计算机系</span><span>蔡木生、王建红、卢坤菲、梁洪宜、高巍巍、冯华、李鹏、张艳玲</span>
<p>C语言是计算机程序设计的主流语言之一，经过40年的不断发展、完善，已成为国内外公认的一种优秀程序设计语言，有着其它语言不可比拟的...</p>
<img src="image/user-empty.png" /><span>225</span><img src="image/chat.png" /><span>28</span>
</div>

</div>

<!-- 分页部分 -->
<div id="page">
<button type="button">&lt;&lt;</button><button type="button">1</button><button type="button">2</button><button type="button">3</button><button type="button">4</button><button type="button">...</button><button type="button">20</button><button type="button">&gt;&gt;</button>
</div>

<!-- 页脚部分 -->
<footer>
<p>版权所有：广州大学华软软件学院</p>
<p>Copyright 🌏 2019 Company name All rights reserved.</p>
</footer>

</body>
</html>