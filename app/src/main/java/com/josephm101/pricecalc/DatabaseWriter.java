package com.josephm101.pricecalc;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseWriter extends AppCompatActivity {
    StringBuilder stringBuilder;
    File logFile;
    BufferedWriter writer;
    private String NewLine = "\r\n";
    private String splitChar = "`";

    public DatabaseWriter() throws IOException {
        //stringBuilder = new StringBuilder();
        logFile = new File(getFilesDir().getParent(), "saved_list.txt");
        logFile.createNewFile();
        try {
            writer = new BufferedWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void AddEntry(DataModel dataModel) throws IOException {
        writer.write(dataModel.getItemName());
        writer.append(splitChar);
        writer.append(dataModel.getItemPrice());
        writer.append(splitChar);
        writer.append(BooleanHandling.BoolToString(dataModel.getIsTaxable(), BooleanHandling.PositiveValue, BooleanHandling.NegativeValue));
        writer.append(splitChar);
        writer.append(BooleanHandling.BoolToString(dataModel.getIsTaxable(), BooleanHandling.PositiveValue, BooleanHandling.NegativeValue));
        writer.newLine();
    }

    private void AppendNewLine() {
        stringBuilder.append(NewLine);
    }
}