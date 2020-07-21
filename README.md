# EpubComicBuilder

__直接下载jar包即可__

制作epub漫画

`````````
Book book = new Book("第一本书");
//作者
book.setAuthor("无镜像");
//封面
book.setCover(new File("F:\\1.jpg"));
//添加图片章节
List<File> picList = new ArrayList<File>();
File[] files = new File("F:\\t").listFiles();
Collections.addAll(picList, files);

Chapter chapter = new Chapter("chapter0",picList);
book.addChapter(chapter);

//添加文字章节
Chapter chapter1 = new Chapter("第二章", "章节内容");
book.addChapter(chapter1);

//添加html章节
List<File> picList = new ArrayList<File>();
File[] files = new File("F:\\t").listFiles();
Collections.addAll(picList, files);
//html包含图片
Chapter chapter_html = new Chapter("html章",new ResourceHtml(new File("F:\\1.html")).withImages(picList));
book.addChapter(chapter_html);


//生成路径
BookWriter bookWriter = new BookWriter(book.build(), new File("F:\\tt\\ttt\\"));
bookWriter.writeBook();
`````````

__注意__
添加html章节，html文件内图片的引用要更改，
例如
```<img src="https://192.306.55/name.jpg" />```
改为
```<img src="images/name.jpg" />```

