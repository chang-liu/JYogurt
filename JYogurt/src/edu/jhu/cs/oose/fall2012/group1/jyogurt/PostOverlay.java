package edu.jhu.cs.oose.fall2012.group1.jyogurt;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

/**
 * The class for overlay on the google map
 * @author Zaoxing (Alan) Liu
 *
 */
@SuppressWarnings("unused")
public class PostOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();

	private Context context;

	public PostOverlay(Drawable defaultMarker, Context context) {
		super(defaultMarker);
		this.context = context;

	}

	/**
	 * The override function for drawing
	 */
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		
		Projection projection = mapView.getProjection();

		for (int index = this.size() - 1; index >= 0; index--) {

			OverlayItem overLayItem = getItem(index);

			Point point = projection.toPixels(overLayItem.getPoint(), null);

			Paint paintText = new Paint();
			paintText.setColor(Color.RED);
			paintText.setTextSize(13);

			canvas.drawText(overLayItem.getTitle(), point.x + 10, point.y - 15,
					paintText);
		}

	}

	/**
	 * The override function for tapping an overlay item
	 */
	@Override
	protected boolean onTap(int index) {
		// TODO Auto-generated method stub
		setFocus(overlayItemList.get(index));
		return super.onTap(index);
	}
	
	/**
	 * The override function for creating an overlay item
	 */
	@Override
	protected OverlayItem createItem(int i) {
		return overlayItemList.get(i);
	}

	/**
	 * The size for overlayItemList's size
	 */
	@Override
	public int size() {
		
		return overlayItemList.size(); 
	}
	/**
	 * The function  for adding overlay
	 * @param overlayItem
	 */
    public void addOverlay(OverlayItem overlayItem) {  
        overlayItemList.add(overlayItem);  
        this.populate();  
    }

}
