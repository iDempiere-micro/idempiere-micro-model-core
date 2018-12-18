SELECT t.Name, t.PO_Name FROM AD_Element_Trl t, AD_Element e
WHERE t.AD_Element_ID=e.AD_Element_ID AND UPPER(e.ColumnName)=?
AND t.AD_Language=?
