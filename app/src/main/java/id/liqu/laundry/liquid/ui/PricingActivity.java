package id.liqu.laundry.liquid.ui;

import android.support.design.widget.TextInputEditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import id.liqu.laundry.liquid.R;
import id.liqu.laundry.liquid.Utils;

/**
 * Created by iamnubs on 22/03/2018.
 */

public class PricingActivity extends BaseActivity {

    @BindView(R.id.t_price)
    TextView price;
    @BindView(R.id.t_weight)
    TextInputEditText weight;

    @Override
    public int layoutId() {
        return R.layout.activity_pricing;
    }

    int w = 0;

    @OnClick(R.id.btn_plus)
    void plus() {
        w++;
        updatePrice();
    }

    @OnClick(R.id.btn_minus)
    void minus() {
        if (w > 0) {
            w--;
        }
        updatePrice();
    }

    private void updatePrice() {
        weight.setText(String.valueOf(w));
        price.setText(Utils.formatRupiah(w * 5000));
    }
}
