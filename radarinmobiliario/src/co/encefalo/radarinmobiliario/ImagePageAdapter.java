package co.encefalo.radarinmobiliario;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class ImagePageAdapter extends PagerAdapter{

		
		private int NUM_AWESOME_VIEWS;
		private Context ctx;
		private ArrayList<String> images;
		private int position;

		public ImagePageAdapter(Context ctx) {
			this.ctx=ctx;
		}

		@Override
		public int getCount() {
			return NUM_AWESOME_VIEWS;
		}

	    /**
	     * Create the page for the given position.  The adapter is responsible
	     * for adding the view to the container given here, although it only
	     * must ensure this is done by the time it returns from
	     * {@link #finishUpdate(android.view.ViewGroup)}.
	     *
	     * @param collection The containing View in which the page will be shown.
	     * @param position The page position to be instantiated.
	     * @return Returns an Object representing the new page.  This does not
	     * need to be a View, but can be some other container of the page.
	     */
		@Override
		public Object instantiateItem(ViewGroup collection, int position) {
			ImageView iv = new ImageView(ctx);

			descargaImagen(images.get(position), iv, null, null);

			this.setPosition(position);
			collection.addView(iv,0);
			
			return iv;
		}

	    

	    public ArrayList<String> getImages() {
			return images;
		}

		public void setImages(ArrayList<String> images) {
			this.images = images;
		}

		public int getNUM_AWESOME_VIEWS() {
			return NUM_AWESOME_VIEWS;
		}

		public void setNUM_AWESOME_VIEWS(int nUM_AWESOME_VIEWS) {
			NUM_AWESOME_VIEWS = nUM_AWESOME_VIEWS;
		}
		/**
	     * Remove a page for the given position.  The adapter is responsible
	     * for removing the view from its container, although it only must ensure
	     * this is done by the time it returns from {@link #finishUpdate(android.view.ViewGroup)}.
	     *
	     * @param collection The containing View from which the page will be removed.
	     * @param position The page position to be removed.
	     * @param view The same object that was returned by
	     * {@link #instantiateItem(android.view.View, int)}.
	     */
		@Override
		public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
		}


        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by instantiateItem(ViewGroup, int). This method is required
         * for a PagerAdapter to function properly.
         * @param view Page View to check for association with object
         * @param object Object to check for association with view
         * @return
         */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return (view==object);
		}

		
	    /**
	     * Called when the a change in the shown pages has been completed.  At this
	     * point you must ensure that all of the pages have actually been added or
	     * removed from the container as appropriate.
	     * @param arg0 The containing View which is displaying this adapter's
	     * page views.
	     */
		@Override
		public void finishUpdate(ViewGroup arg0) {
			
		}
		

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(ViewGroup arg0) {
			
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}
		
		private void descargaImagen(final String imagen, final ImageView iv, final ProgressBar pb, final LinearLayout ll) {
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
