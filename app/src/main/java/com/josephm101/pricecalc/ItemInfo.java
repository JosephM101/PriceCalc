package com.josephm101.pricecalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ItemInfo extends AppCompatActivity {
    DataModel importedDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.item_info);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        //Init Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        Log.i("ItemInfo_INIT", "Importing data model...");
        Intent i = getIntent();
        importedDataModel = (DataModel) i.getSerializableExtra("dataModel");
        Log.i("ItemInfo_INIT", "Defining entries...");
        TextView ItemNameLabel = findViewById(R.id.itemNameLabel);
        TextView ItemPriceLabel = findViewById(R.id.itemPriceLabel);
        TextView ItemQuantityLabel = findViewById(R.id.itemQuantityLabel);
        TextView ItemTaxDeductionLabel = findViewById(R.id.itemTaxableLabel);
        TextView ItemTotalCostLabel = findViewById(R.id.itemTotalCost);
        Log.i("ItemInfo_INIT", "Reading & writing values...");
        ItemNameLabel.setText(importedDataModel.getItemName());
        double itemPrice = Double.parseDouble(importedDataModel.getItemPrice());
        int itemQuantity = Integer.parseInt(importedDataModel.itemQuantity);
        ItemPriceLabel.setText(PriceHandling.PriceToString(itemPrice));
        ItemQuantityLabel.setText("Quantity: " + importedDataModel.itemQuantity);
        ItemTaxDeductionLabel.setText("Is taxable: " + BooleanHandling.BoolToString(importedDataModel.getIsTaxable(), "Yes", "No"));
        StringBuilder stringBuilder = new StringBuilder();
        double priceWithQuantity = (itemPrice * itemQuantity);
        stringBuilder.append(PriceHandling.PriceToString(itemPrice * itemQuantity));
        stringBuilder.append("\r\n");
        stringBuilder.append("Tax cost: ");
        double priceTax = ((priceWithQuantity * PriceHandling.getDefaultTaxDeductionPercentage(this)) / 100);
        stringBuilder.append(PriceHandling.PriceToString(priceTax));
        stringBuilder.append("\r\n");
        stringBuilder.append("\r\n");
        stringBuilder.append("Total overall cost: ");
        double totalCostOverall = priceWithQuantity + priceTax;
        stringBuilder.append(PriceHandling.PriceToString(totalCostOverall));
        ItemTotalCostLabel.setText(stringBuilder.toString());
        Log.i("ItemInfo_INIT", "Init done; calculations complete.");
        Log.i("ItemInfo_INIT", "All values printed.");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}