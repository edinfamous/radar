package co.encefalo.radarinmobiliario;

import java.util.ArrayList;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
			
			Utilidades util = new Utilidades(ctx);
			util.descargaImagen(images.get(position), iv, null, null);

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

    	
    }
