package com.aplicativo.henrique.urbanexplorer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class listViewAdapterAvaliacao extends BaseAdapter {
    private Context context;
    private ArrayList<Lugar> beanClassArrayList;





    public listViewAdapterAvaliacao(Context context, ArrayList<Lugar> beanClassArrayList) {
        this.context = context;
        this.beanClassArrayList = beanClassArrayList;


    }


    @Override
    public int getCount() {
        return beanClassArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanClassArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        listViewAdapterAvaliacao.ViewHoder viewHoder;
        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview, parent, false);

            viewHoder = new listViewAdapterAvaliacao.ViewHoder();


            viewHoder.image = (ImageView) convertView.findViewById(R.id.market);
            viewHoder.title = (TextView) convertView.findViewById(R.id.sport);



            convertView.setTag(viewHoder);

        } else {

            viewHoder = (listViewAdapterAvaliacao.ViewHoder) convertView.getTag();
        }

        //  NavigationItems rowItem = (NavigationItems) getItem(position);

        Lugar beanClass = (Lugar) getItem(position);

        if(beanClass instanceof Cidade){
            viewHoder.title.setText(beanClass.getNome());
        }
        else{

            viewHoder.title.setText(beanClass.getNome()+ " - "+String.format("%.01f", beanClass.getAvalicao())+ "/5,0");
        }

        //viewHoder.description.setText(beanClass.getDescription());

        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), beanClass.getImagem());
        //viewHoder.image.setImageBitmap(getRoundedCornerBitmap(bitmap, 20));

        //viewHoder.image.setImageResource(beanClass.getImagem());
        Picasso.with(context).load(Uri.parse(beanClass.getImg())).into(viewHoder.image);

//        viewHoder.plus.setImageResource(beanClass.getImage());
//        viewHoder.min.setImageResource(beanClass.getImage());
        //viewHoder.no.setText(beanClass.getNo());


        return convertView;

    }


    private class ViewHoder{

        ImageView image;
        TextView title;
        TextView description;


    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
