package com.application.bris.ikurma_nos_gadai.page_aom.listener;

import com.application.bris.ikurma_nos_gadai.database.pojo.ProductPojo;

/**
 * Created by PID on 05/05/19.
 */

public interface ProductListener {
    void onProductSelect(String title, ProductPojo data);
}
