SELECT s.CurrentNext, s.CurrentNextSys
FROM AD_Sequence s
WHERE s.AD_Sequence_ID = ?
AND s.IsActive='Y' AND s.IsTableID='N' AND s.IsAutoSequence='Y'
ORDER BY s.AD_Client_ID DESC
FOR UPDATE OF s