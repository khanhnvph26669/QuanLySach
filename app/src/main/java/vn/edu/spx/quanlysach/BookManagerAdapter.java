package vn.edu.spx.quanlysach;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.spx.quanlysach.DAO.BookManagerDAO;
import vn.edu.spx.quanlysach.model.BookManager;

public class BookManagerAdapter extends BaseAdapter {
    List<BookManager> lsBook;
    Context context;
    LayoutInflater inflater;
    BookManagerDAO dao;

    public BookManagerAdapter(Context context, List<BookManager> lsBook) {
        super();
        this.context = context;
        this.lsBook = lsBook;
        this.inflater
                = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return lsBook.size();
    }

    @Override
    public Object getItem(int position) {
        return lsBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        BookManager bm = lsBook.get(position);
        dao = new BookManagerDAO(context);
        if (view == null) {
            inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_book, null);
        }
        TextView txtTenSach, txtSoLuong;
        ImageButton btnXoaBM;
        btnXoaBM = view.findViewById(R.id.igBtnCancelBook);

        txtTenSach = view.findViewById(R.id.txtItemTenSach);
        txtSoLuong = view.findViewById(R.id.txtItemSoLuong);

        txtTenSach.setText("Tên sách: "+bm.getTenSach());
        txtSoLuong.setText("Số lượng: "+bm.getSoLuong());
        btnXoaBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteBM(bm.getMaSach());
                lsBook.remove(position);
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                lsBook.clear();
                lsBook = dao.getAllBook();
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
