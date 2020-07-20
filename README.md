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
//生成路径
BookWriter bookWriter = new BookWriter(book.build(), new File("F:\\tt\\ttt\\"));
bookWriter.writeBook();
`````````
