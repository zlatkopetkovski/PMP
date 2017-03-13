
package com.fikt.pmp.homework2;


/**
 * Created by Zlatko on 3/12/2017.
 */


        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final List<String> web;
    private final List<Integer> imageId;
    public CustomList(Activity context,
                      List<String> web, List<Integer> imageId) {
        super(context, R.layout.mylist, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
  /*  @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mylist, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web.get(position));

        imageView.setImageResource(imageId.get(position));
        return rowView;
    }*/
}

