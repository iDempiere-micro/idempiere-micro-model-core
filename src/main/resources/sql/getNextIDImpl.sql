SELECT CurrentNext, CurrentNextSys, IncrementNo, AD_Sequence_ID
FROM AD_Sequence
WHERE Name=?
  AND IsActive = 'Y'
  AND IsTableID = 'Y'
  AND IsAutoSequence = 'Y' FOR UPDATE OF AD_Sequence
