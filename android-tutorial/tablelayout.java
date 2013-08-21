-------------------- [ java ]--------------------
- Collapses or restores a given column.
setColumnCollapsed

-------------------- [ ran time ][ insert new row ]--------------------
TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

TableLayout tableLayout = new TableLayout(context);
tableLayout.setLayoutParams(this.tableParams);

TableRow tableRow = new TableRow(context);
tableRow.setLayoutParams(this.tableParams);

TextView textView = new TextView(context);
textView.setLayoutParams(this.rowParams);

tableRow.addView(textView);


-------------------- [ xml ]--------------------
<TableLayout
  android:layout_width="fill_parent"
  android:layout_height="wrap_content"
  android:stretchColumns="0,1" >
   <TableRow android:layout_width="fill_parent">
     <TextView
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       android:text="ok"
      />
    <TextView
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="bye" />
   </TableRow>
</TableLayout>
