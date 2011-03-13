package kn.example;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class mycustomview extends TextView {
	private Paint marginPaint;
	private Paint linePaint;
	private int paperColor;
	private float margin;

	public mycustomview(Context context) {
		super(context);
		myinit();
		// TODO Auto-generated constructor stub
	}
	
	public mycustomview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		myinit();
	}
	
	public mycustomview(Context context, AttributeSet attrs) {
		super(context, attrs);
		myinit();
	}

	private void myinit() {
		Resources myRes = getResources();
		
		/// create the paint brushes to be used by onDraw()
		marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		marginPaint.setColor(myRes.getColor(R.color.mymargin));
		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		linePaint.setColor(myRes.getColor(R.color.myline));
		paperColor = myRes.getColor(R.color.mypaper);
		margin = myRes.getDimension(R.dimen.margin);
		
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(paperColor);
		canvas.drawLine(0, 0, getMeasuredHeight(), 0, linePaint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredHeight(), 
				getMeasuredHeight(), linePaint);
		/// draw margin
		canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);
		/// Move the text across from the margin
		canvas.save();
		canvas.translate(margin, 0);
		
		/// use the textview to render the text
		super.onDraw(canvas);
		canvas.restore();
	}
   
}