// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.icarexm.jiediuser.custview.mywheel;

import android.view.MotionEvent;

import com.icarexm.jiediuser.custview.mywheel.MyWheelView;

final class WheelViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final MyWheelView wheelView;

    WheelViewGestureListener(MyWheelView wheelview) {
        wheelView = wheelview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        wheelView.scrollBy(velocityY);
        return true;
    }
}
