SELECT y.CurrentNext, s.CurrentNextSys
FROM AD_Sequence_No y, AD_Sequence s
WHERE y.AD_Sequence_ID = s.AD_Sequence_ID
AND s.AD_Sequence_ID = ?
AND y.CalendarYearMonth = ?
AND y.AD_Org_ID = ?
AND s.IsActive='Y' AND s.IsTableID='N' AND s.IsAutoSequence='Y'
ORDER BY s.AD_Client_ID DESC
FOR UPDATE OF y