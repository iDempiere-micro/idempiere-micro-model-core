UPDATE AD_Sequence_No
SET CurrentNext = CurrentNext + ?
WHERE AD_Sequence_ID=?
  AND CalendarYearMonth=?
  AND AD_Org_ID=?