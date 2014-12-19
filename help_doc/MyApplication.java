编码：utf-8

File cacheDir = StorageUtils.getCacheDirectory(context,
"UniversalImageLoader/Cache");

ImageLoaderConfiguration config = new
ImageLoaderConfiguration .Builder(getApplicationContext())
.maxImageWidthForMemoryCache(800)
.maxImageHeightForMemoryCache(480)
.httpConnectTimeout(5000)
.httpReadTimeout(20000)
.threadPoolSize(5)
.threadPriority(Thread.MIN_PRIORITY + 3)
.denyCacheImageMultipleSizesInMemory()
.memoryCache(new UsingFreqLimitedCache(2000000)) // You can pass your own memory cache implementation
.discCache(new UnlimitedDiscCache(cacheDir)) // You can pass your own disc cache implementation
.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
.build();

下面让我们来看看每一个选项。

• maxImageWidthForMemoryCache() 和maxImageHeightForMemoryCache()用于将图片将图片解析成Bitmap对象。为了不储存整个图片，根据ImageView参数的值（要加载图片的那个）减少图片的大小。maxWidth和maxHeight(第一阶段),layout_width layout_height(第二阶段)。如果不定义这些参数(值为fill_parent和wrap_content被视为不确定的大小),然后尺寸的设定就会根据maxImageWidthForMemoryCache()和maxImageHeightForMemoryCache()的设置而定。原始图像的大小最大会缩小到2倍(适合用fast decoding),直到宽度或高度变得小于指定值;

o默认值 - 设备的屏幕大小

• httpReadTimeout()设置图片从网络中加载的最大超时时间（以毫秒为单位）

o 默认值- 30秒

• threadPoolSize() 设置线程池的大小. 每一个图片加载和显示的任务都是在一个个单独的线程中进行的，这些线程在从图片网络中被下载的时候就会进入线程池。因此，池的大小决定能同时运行的线程数。一个大的线程池能显著地拖慢UI的响应速度，例如，列表的滚动就会变慢. 
o 默认值- 5

• threadPriority()设置正在运行任务的所有线程在系统中的优先级（1到10）；

o默认值- 4

• 调用denyCacheImageMultipleSizesInMemory()强制UIL在内存中不能存储内容相同但大小不同的图像。由于完整大小的图片会存储在磁盘缓存中，后面当图片加载进入内存，他们就会缩小到ImageView的大小（图片要显示的尺寸），然而在某些情况下,相同的图像第一次显示在一个小的View中,然后又需要在一个大的View中显示。同时,两个不同大小的相同内容的图片就会被将被存储在内存中。这是默认的操作。denyCacheImageMultipleSizesInMemory()指令确保删除前一个加载的图像缓存的内存的大小

• 使用memoryCache(),你可以指定内存缓存的实现。你可以使用现成的解决方案(他们都是实现limited  size-cache,如果超过缓存大小,就通过一定算法删除一个对象):

o FIFOLimitedCache (根据先进先出的原则上删除多余对象)
o LargestLimitedCache (空间占用最大的对象会被删除)
o UsingAgeLimitedCache (最早被添加的对象会被删除)
o UsingFreqLimitedCache (最少被用到的对象会被删除)
或者,你可以实现通过实现接口MemoryCacheAware <String,Bitmap>来实现自己的缓存；
o 默认值 - 使用2 MB的内存限制的UsingFreqLimitedCache
memoryCacheSize() 设置内存缓存的最大占用空间. 在这种情况下，默认的缓存策略是 - UsingFreqLimitedCache.
o 默认值 - 2 MB

• Using discCache(), 你可以定义自己的磁盘缓存. 也可以现成的解决方案 (文件跟特定的URL匹配，文件名为这些URL的哈希值):

o UnlimitedDiscCache (通常的策略, 缓存大小没有限制)
o FileCountLimitedDiscCache (限定大小的缓存)
o TotalSizeLimitedDiscCache (限定文件个数的缓存策略)
另外,你也可以通过实现DiscCacheAware接口定义自己的缓存
o 默认值 - UnlimitedDiscCache

• 使用defaultDisplayImageOptions(),你可以设置image显示选项,如果自定义选项没有传递给displayImage(),它将用于displayimage(…)方法的每一次调用。下面,我将详细讨论这些选项。

我们可以构建一个configuration对象或信任一个开发人员(比如我)然后使用默认的configuration:

 

ImageLoaderConfiguration config =
ImageLoaderConfiguration.createDefault(context);
 

因此,configuration就创建好了。现在,ImageLoader可以通过它初始化了:

ImageLoader.getInstance().init(config);

就这样,ImageLoader已经可以使用了。