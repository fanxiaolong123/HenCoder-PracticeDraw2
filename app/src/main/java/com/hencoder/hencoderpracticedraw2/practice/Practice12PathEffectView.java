package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        PathEffect pathEffect = null;
        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        pathEffect = new CornerPathEffect(25);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);

        // 第二处：DiscretePathEffect
        canvas.save();
        canvas.translate(500, 0);
        pathEffect = new DiscretePathEffect(20, 5);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // 第三处：DashPathEffect
        canvas.save();
        canvas.translate(0, 200);
        pathEffect = new DashPathEffect(new float[]{20, 10 , 5, 10}, 0);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // 第四处：PathDashPathEffect
        canvas.save();
        canvas.translate(500, 200);
        Path shape = new Path();
        shape.rLineTo(35, 0);
        shape.rLineTo(-17.5f, (float) (Math.cos(60) * 35));
        shape.close();
        pathEffect = new PathDashPathEffect(shape, 42, 0, PathDashPathEffect.Style.MORPH);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // 第五处：SumPathEffect
        canvas.save();
        canvas.translate(0, 400);
        pathEffect = new DashPathEffect(new float[]{20, 10 , 5, 10}, 0);
        PathEffect pathEffect2 = new DiscretePathEffect(20, 5);
        SumPathEffect sumPathEffect = new SumPathEffect(pathEffect, pathEffect2);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // 第六处：ComposePathEffect
        canvas.save();
        canvas.translate(500, 400);
        ComposePathEffect composePathEffect = new ComposePathEffect(pathEffect, pathEffect2);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();
    }

}
