SELECT TableName
FROM AD_Table t
WHERE IsActive='Y' AND IsView='N'
AND AD_Table_ID IN
(SELECT AD_Table_ID FROM AD_Column
WHERE ColumnName = 'DocumentNo' OR ColumnName = 'Value')
 AND 'DocumentNo_' || TableName NOT IN
(SELECT Name FROM AD_Sequence s
WHERE s.AD_Client_ID=?)