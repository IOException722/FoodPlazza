package matrimony.foodplazza;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ActionBarActivity {
    ListView mListView;
    ArrayList<Boolean> mArrayboolean;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> mMianCourseList;
    ArrayList<String> mStarterList;
    ArrayList<String> mDesertList;
    ArrayList<Integer> mFoodImageList;
    LayoutInflater mInflater;
    MyAdapter myAdapter;
    ArrayList<Integer> quantitystarter;
    ArrayList<Integer> quantitymaincourse;
    ArrayList<Integer> quantitydesert;
    ArrayList<Boolean> boolMaincourse;
    ArrayList<Boolean> boolStarter;
    ArrayList<Boolean> boolDesert;
    
    
    Spinner mDyanamicSpinner;
    SpinnerAdapter mSpinnerAdapter;
    Integer pos, size1, size2, size3;
    ArrayList<String> mSpinnerList;
    //HashMap<Boolean, HashMap<>>;
    ArrayList<String> mSpinnerList2;
    DrawerLayout mDrawerLayout;

    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView= (ListView) findViewById(R.id.list_view_1);

        quantitystarter = new ArrayList<Integer>();
        quantitydesert = new ArrayList<Integer>();
        quantitymaincourse = new ArrayList<Integer>();

        for(int i=0;i<10;i++)
        {
            quantitystarter.add(0);
            quantitydesert.add(0);
            quantitymaincourse.add(0);
        }

        mFoodImageList = new ArrayList<Integer>(Arrays.asList(DataClass.imageData));
        mMianCourseList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.food_starter)));
        mStarterList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.main_course)));
        mDesertList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.deserts)));
        mDyanamicSpinner = (Spinner)findViewById(R.id.spinner1);
        mSpinnerList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.array_menu)));
        mSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mSpinnerList);
        mDyanamicSpinner.setAdapter(mSpinnerAdapter);
        boolMaincourse = new ArrayList<Boolean>();
        boolDesert=new ArrayList<Boolean>();
        boolStarter=new ArrayList<Boolean>();
        size = mSpinnerList.size();
        size1  = mMianCourseList.size();
       size2 = mStarterList.size();
        size3 = mDesertList.size();
        mInflater = getLayoutInflater();

        for(int i=0;i<size1;i++)
        {
            boolMaincourse.add(false);
        }
        for(int i=0;i<size2;i++)
        {
            boolStarter.add(false);
        }
        for(int i=0;i<size3;i++)
        {
            boolDesert.add(false);
        }

        mDyanamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item selected", mSpinnerList.get(position).toString());
                Log.v("position is", Integer.toString(position));
                pos = position;
                callAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Log.v("nothing", "nothing");
            }
        });


        //mFoodPrice = new ArrayList<Integer>(Arrays.asList(getResources().getIntArray(R.array.foodquantitystarter)));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                Log.v("ON SLIDE", drawerView.toString());
                Log.v("FLOAT OFFSET",Float.toString(slideOffset));
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.v("ON OPENED", drawerView.toString());
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.v("ON CLOSED", drawerView.toString());
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.v("ON STATE CHANGED",Integer.toString(newState));
            }
        });
    }
    public void callAdapter()
    {
        myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    public ArrayList<Boolean> changeBoolValue(int position, ArrayList<Boolean> arr, Integer size)
    {
        int i;
        for(i=0;i<size;i++)
        {
            if(i!=position)
            {
                arr.set(i, false);
            }
        }
        myAdapter.notifyDataSetChanged();
        return arr;
    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // get size of array to populate from DataClass, no initializations required because
            // ARRAY_LENGTH is a static variable
            return DataClass.ARRAY_LENGTH;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null){

                convertView = mInflater.inflate(R.layout.starter,parent,false);
            }
            ImageView imageView = (ImageView)convertView.findViewById(R.id.foodimage);
            imageView.setImageResource(mFoodImageList.get(position));

            switch(pos)
            {
                case 0:
                    TextView fooditem = (TextView) convertView.findViewById(R.id.fooditem);
                    fooditem.setText(mMianCourseList.get(position));
                    TextView qty = (TextView) convertView.findViewById(R.id.quantity);
                    qty.setText(quantitystarter.get(position).toString());
                    break;

                case 1:
                    TextView foodmaincourse = (TextView) convertView.findViewById(R.id.fooditem);
                    foodmaincourse.setText(mStarterList.get(position));

                    TextView qtymaincourse = (TextView) convertView.findViewById(R.id.quantity);
                    qtymaincourse.setText(quantitymaincourse.get(position).toString());

                    break;
                default:
                    TextView deserts = (TextView) convertView.findViewById(R.id.fooditem);
                    deserts.setText(mDesertList.get(position));

                    TextView qtydeserts = (TextView) convertView.findViewById(R.id.quantity);
                    qtydeserts.setText(quantitydesert.get(position).toString());

            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch(pos)
                    {
                        case 0:
                            boolStarter.set(position, true);
                            quantitystarter.set(position, quantitystarter.get(position) + 1);
                            boolStarter = changeBoolValue(position, boolStarter, size2);
                            break;
                        case 1:
                            boolMaincourse.set(position, true);
                            quantitymaincourse.set(position, quantitymaincourse.get(position) + 1);
                            boolMaincourse = changeBoolValue(position, boolMaincourse, size1);
                            break;
                        default:
                            boolDesert.set(position, true);
                            quantitydesert.set(position, quantitydesert.get(position)+1);
                            boolDesert = changeBoolValue(position, boolDesert, size2);
                    }
                    Log.v("onclick+position", Integer.toString(pos)+" pos"+ Integer.toString(position));
                }
            });

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    switch(pos)
                    {
                        case 0:
                            if(quantitystarter.get(position)>0)
                                quantitystarter.set(position,quantitystarter.get(position)-1);
                                callAdapter();
                            break;
                        case 1:
                            if(quantitymaincourse.get(position)>0)
                                quantitymaincourse.set(position, quantitymaincourse.get(position)-1);
                                callAdapter();
                            break;
                        default:
                            if(quantitydesert.get(position)>0)
                                quantitydesert.set(position, quantitydesert.get(position)-1);
                                callAdapter();
                    }

                    Log.v("onlongclick+position", Integer.toString(pos)+" pos"+ Integer.toString(position));
                    return true;
                }
            });

            switch (pos)
            {
                case 0:
                    if(boolStarter.get(position))
                        convertView.setBackgroundColor(Color.BLUE);
                    else
                        convertView.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case 1:
                    if(boolMaincourse.get(position))
                        convertView.setBackgroundColor(Color.BLUE);
                    else
                        convertView.setBackgroundColor(Color.TRANSPARENT);
                    break;
                default:
                    if(boolDesert.get(position))
                        convertView.setBackgroundColor(Color.BLUE);
                    else
                        convertView.setBackgroundColor(Color.TRANSPARENT);


            }

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
