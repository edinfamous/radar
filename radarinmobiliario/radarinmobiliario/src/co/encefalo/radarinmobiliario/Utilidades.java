package co.encefalo.radarinmobiliario;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class Utilidades {

	private Context ctx;

	public Utilidades(Context ctx) {
		this.ctx=ctx;
	}

	public void descargaImagen(final String imagen, final ImageView iv, final ProgressBar pb, final LinearLayout ll) {
		 int availableMemory = (int) Runtime.getRuntime().freeMemory();
		 
		 Log.d("MEMORIA",(availableMemory/2) * 1024 * 1024+"");
		
		DisplayImageOptions options = 
				new DisplayImageOptions.Builder().
				bitmapConfig(Bitmap.Config.RGB_565).
				cacheInMemory(false).
				cacheOnDisk(true).
				imageScaleType(ImageScaleType.EXACTLY).
				build();
		
		File cacheDir = StorageUtils.getCacheDirectory(ctx);
		
		ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(ctx).threadPoolSize(1)
					.defaultDisplayImageOptions(options)
					.diskCacheExtraOptions(480, 320, null)
					.denyCacheImageMultipleSizesInMemory()
					.diskCache(new UnlimitedDiscCache(cacheDir))
					//.diskCacheSize(64000000)
					.memoryCache(new WeakMemoryCache())
					.memoryCacheSize((availableMemory/4))
					.build(); 
		
		final ImageLoader imageLoader = ImageLoader.getInstance();
		
		ImageLoadingListener ill = new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				if (pb!=null){
					pb.setVisibility(View.VISIBLE);

				}
			}
			
			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				Log.d("onLoadingFailed",arg0+" "+arg2.getCause());
				descargaImagen(imagen, iv, pb, ll);
				//pb_youtube.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap bm) {
				//Log.d("onLoadingComplete",arg0);
				iv.setVisibility(View.VISIBLE);
				if (pb!=null){
					pb.setVisibility(View.GONE);
				}
				if (ll!=null){
					ll.setVisibility(View.VISIBLE);
				}
				
				
				imageLoader.clearMemoryCache();
				/*float wl = ll_noticia.getWidth();
				//int hl = ll_noticia.getHeight();
				
				
				float w = bm.getWidth();
				float h = bm.getHeight();
				
				float scale =(float)ll_noticia.getWidth()/(float)w;
				
				Log.d("ESCALE", scale+" "+w+" "+h+" "+wl);
				
				LinearLayout.LayoutParams llp= new LinearLayout.LayoutParams((int)wl, (int) (scale*h));		
				iv.setLayoutParams(llp);
				*/
			}
			
			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
			}
		};
		
		imageLoader.init(imageLoaderConfiguration);
		imageLoader.displayImage(imagen, iv, ill);


		
	}

}
