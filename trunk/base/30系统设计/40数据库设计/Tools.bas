Attribute VB_Name = "Tools"
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' ˝æ›ø‚”ÎJFPøÚº‹¥˙¬Î∆¨∂Œ…˙≥…π§æﬂ
'
'∞Ê±æ 0.2 2012-9-19 …˝º∂Œ™MyBatis 3.1œµ¡–XML◊ˆ≥…
'◊˜’ﬂ Spook
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

Const dataStartLine = 7
Const dataEndLine = 100

Const itemNameCol = 3
Const fieldNameCol = 20
Const propertiesCol = 31
Const numberCol = 36
Const keyCol = 39
Const mustCol = 42
Const defaultCol = 46


Dim SYSNAME        As String
Dim spgname         As String
Dim sdbname         As String
Dim sfxdate         As String
Dim sver           As String
Dim sclName           As String

Dim itemValue          As String
Dim fieldValue    As String
Dim numberValue          As String
Dim mustValue          As String
Dim propertiesValue          As String
Dim keyValue          As String
Dim defaultValue      As String

Dim result           As Boolean
    
Public sfileName As String
Sub CreatField()

On Error GoTo Open_Error
   
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    Dim strKeyComm      As String
    
    Dim strItem          As String
    Dim strUpdateVal     As String
    Dim strInsertVal     As String
    Dim strWhereVal      As String
    
    Application.ScreenUpdating = False
       
    'Head .............
    For i = dataStartLine To 100     'ø™ º—≠ª∑
        WK_Str = ""
        itemValue = Cells(i, 10).Value
        If itemValue <> "" Then
            Cells(i, 45).Value = getPinyinValue(itemValue)
        End If
        
    Next i
   
    
    MsgBox ("÷¥––ÕÍ¡À")
    
    Exit Sub
    
Open_Error:
    MsgBox ("«Î‘Ÿ¥Œ‘À––")
End Sub

'''
'''ªÒµ√∫∫◊÷µƒ∆¥“Ù¬Î
'''
Function getPinyinValue(ByVal x As String) As String
On Error Resume Next
Dim i As Integer
For i = 1 To Len(x)
    If Mid(x, i, 1) <> " " And Asc(Mid(x, i, 1)) < 0 Then getPinyinValue = getPinyinValue & Mid("ABCDEFGHJKLMNOPQRSTWXYZZ", Evaluate("MATCH(" & Asc(Mid(x, i, 1)) & ",{-20319,-20283,-19775,-19218,-18710,-18526,-18239,-17922,-17417,-16474,-16212,-15640,-15165,-14922,-14914,-14630,-14149,-14090,-13318,-12838,-12556,-11847,-11055,-10247},1)"), 1)
Next
End Function


Function getSQLValue(line As Integer) As Boolean
       
    itemValue = Trim(Cells(line, itemNameCol).Value)
    fieldValue = Trim(Cells(line, fieldNameCol).Value)
    numberValue = Trim(Cells(line, numberCol).Value)
    propertiesValue = UCase(Trim(Cells(line, propertiesCol).Value))
    mustValue = UCase(Trim(Cells(line, mustCol).Value))
    keyValue = UCase(Trim(Cells(line, keyCol).Value))
    defaultValue = Trim(Cells(line, defaultCol).Value)

    If fieldValue = "" Then
        getSQLValue = False
    Else
        fieldValue = UCase(fieldValue)
        getSQLValue = True
    End If
    
End Function

Function getEntityValue(line As Integer) As Boolean
       
    itemValue = Trim(Cells(line, itemNameCol).Value)
    fieldValue = Trim(Cells(line, fieldNameCol).Value)
    numberValue = Trim(Cells(line, numberCol).Value)
    propertiesValue = (Trim(Cells(line, propertiesCol).Value))
    mustValue = UCase(Trim(Cells(line, mustCol).Value))
    keyValue = UCase(Trim(Cells(line, keyCol).Value))
    defaultValue = Trim(Cells(line, defaultCol).Value)

    If fieldValue = "" Then
        getEntityValue = False
    Else
        fieldValue = upFirstStr(fieldValue)
        getEntityValue = True
    End If
    
End Function


Function upFirstStr(SynStr As String) As String
     upFirstStr = UCase(Left(SynStr, 1)) & (Right(SynStr, Len(SynStr) - 1))
End Function

Function downFirstStr(SynStr As String) As String
     downFirstStr = LCase(Left(SynStr, 1)) & (Right(SynStr, Len(SynStr) - 1))
End Function
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatSQL()

    SYSNAME = Cells(1, 52).Value
    spgname = Cells(1, 31).Value
    sdbname = SYSNAME & Cells(1, 62).Value
    'sver = Cells(4, 78).Value
       
    sfxdate = Format(Date, "YYYY/MM/DD")

    'ªÒµ√Œƒº˛√˚≥∆
    sfileName = Application.GetSaveAsFilename(InitialFilename:=sdbname & "", _
                          fileFilter:="sql , *.sql")
    
    If sfileName = "" Or sfileName = "False" Then
        MsgBox ("«Î÷∏∂®Œƒº˛√˚≥∆")
         Exit Sub
    End If
        
On Error GoTo Open_Error
    Const OBJ_EXT = ".SQL"
    If UCase(Right(sfileName, Len(OBJ_EXT))) = OBJ_EXT Then
        sfileName = Left(sfileName, Len(sfileName) - Len(OBJ_EXT))
    End If
    
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & ".sql" For Output Shared As #1
    
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
    Dim strKeyComm      As String
    
    Dim strItem          As String
    Dim strUpdateVal     As String
    Dim strInsertVal     As String
    Dim strWhereVal      As String
    
    Application.ScreenUpdating = False
    
    Print #1, "CREATE TABLE " & LCase(sdbname)
    Print #1, "("
    
    'Head .............
    For i = dataStartLine To dataEndLine     'ø™ º—≠ª∑
        WK_Str = ""
        If Not getSQLValue(i) Then
            Exit For
        End If
    
        '/********** ‰≥ˆ◊÷∂ŒœÓƒø**********/
        'SQLID VARCHAR2(200) DEFAULT 0
        If fieldValue <> "" Then
            If strItem <> "" Then
                strItem = strItem & " , " & fieldValue
                strInsertVal = strInsertVal & " , " & "?"
                strUpdateVal = strUpdateVal & " ; " & fieldValue & " = ?"
            Else
                strItem = fieldValue
                strInsertVal = "?"
                strUpdateVal = fieldValue & " = ?"
            End If
        End If
        
        'πÿº¸◊÷
        If keyValue <> "" Then
            If strKeyName <> "" Then
                strKeyName = strKeyName & " , " & fieldValue
                strWhereVal = strWhereVal & " ; " & fieldValue & " = ?"
            Else
                strKeyName = fieldValue
                strWhereVal = fieldValue & " = ?"
            End If
            
            mustValue = "Y"
            Range("AO" & i).Select   '& ":AQ" & i
            ActiveCell.FormulaR1C1 = "Y"
        End If
        
        WK_Str = "    " & LCase(fieldValue)
        
        '¿‡–Õ
        If propertiesValue = "DATE" Or propertiesValue = "LONG" Then
            WK_Str = WK_Str & " " & propertiesValue
        ElseIf propertiesValue = "VARCHAR2" Then
            WK_Str = WK_Str & " " & "VARCHAR" & "(" & numberValue & ")"
        Else
            WK_Str = WK_Str & " " & propertiesValue & "(" & numberValue & ")"
        End If
        
        'ƒ¨»œ÷µ
        If defaultValue <> "" Then
            WK_Str = WK_Str & " DEFAULT '" & defaultValue & "'"
        End If

        'ø’
        If mustValue <> "" Then
            WK_Str = WK_Str & " NOT NULL"
        End If

        'MySQL
        'WK_Str = WK_Str & " COMMENT " & "'" & itemValue & "'"

        'SQL
        WK_Str = WK_Str & ","
'        Print #1, "    --" & itemValue
        Print #1, WK_Str
        
    Next i
    'OACLE ◊¢Ω‚
    If strKeyName = "" Then
        strKeyName = "SEQNO"
        strKeyComm = "COMMENT ON COLUMN " & LCase(sdbname) & "." _
                & strKeyName & " IS 'SQL'"
     '   Print #1, "    --SQLî‘çÜ"
        Print #1, "    SEQNO VARCHAR2(12) NOT NULL,"
    End If
    
    'Print #1, "    --ÉLÅ[èÓïÒ"
    WK_Str = "PRIMARY KEY (" & LCase(strKeyName) & ")"
    Print #1, WK_Str
    
    Print #1, ")"
        
    Close #1
    
    MsgBox ("Œƒº˛◊ˆ≥…ÕÍ¡À")
    
    Exit Sub
    
Open_Error:
    MsgBox ("Œƒº˛◊ˆ≥… ß∞‹" & Chr(13) & _
        "«Î‘Ÿ¥Œ‘À––")
End Sub


Sub CreatXML()

    SYSNAME = Cells(1, 52).Value
    spgname = Cells(1, 31).Value
    sdbname = SYSNAME & Cells(1, 62).Value
    'sver = Cells(4, 78).Value
       
    sfxdate = Format(Date, "YYYY/MM/DD")

    'ªÒµ√Œƒº˛√˚
    sfileName = Application.GetSaveAsFilename(InitialFilename:=sdbname & "", _
                          fileFilter:="xml , *.xml")
    
    If sfileName = "" Or sfileName = "False" Then
        MsgBox ("«Î÷∏∂®Œƒº˛√˚≥∆")
         Exit Sub
    End If
        
On Error GoTo Open_Error
    Const OBJ_EXT = ".XML"
    If UCase(Right(sfileName, Len(OBJ_EXT))) = OBJ_EXT Then
        sfileName = Left(sfileName, Len(sfileName) - Len(OBJ_EXT))
    End If
    
    Dim WK_Str      As String
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & ".xml" For Output Shared As #1
    Dim i           As Integer
    Dim sTemp As String
    'Àı¬‘√˚∂®“Â
    sTemp = "'" & sdbname & "DBO'"
    
    '…˙≥…Àı¬‘√˚
    'Print #1, "<!-- Need to Copy Into Config XML(mybatis.xml) file -->"
    'Print #1, "<!-- " & Trim(Cells(1, 31).Value) & " -->"
    'Print #1, "<typeAlias alias=" & sTemp & " type='org.jfp.business.xxx.example.bean." & sdbname & "DBO'/>"
    'Print #1, ""
    
    '…˙≥…Õ∑Œƒº˛
    Print #1, "<?xml version='1.0' encoding='UTF-8' ?> "
    Print #1, "<!DOCTYPE mapper"
    Print #1, "    PUBLIC '-//mybatis.org//DTD Dao 3.0//EN' "
    Print #1, "    'http://mybatis.org/dtd/mybatis-3-mapper.dtd' >"
    Print #1, "<!-- " & Trim(Cells(1, 31).Value) & " -->"
    Print #1, "<!-- –Ë“™∫ÕDAO±£≥÷ÕÍ»´¬∑æ∂“ª÷¬ -->"
    Print #1, "<mapper namespace='." & sdbname & "Dao'>"
    
    ' ˝æ›±Ì”≥…‰
    'Print #1, "    <!--  ˝æ›±Ì”≥…‰ -->"
    'Print #1, "    <resultMap type=" & sTemp & " id='resultMapFromDate'>"
   ' For i = dataStartLine To dataEndLine     '—≠ª∑¥¶¿Ì
   '     If Not getEntityValue(i) Then
   '         Exit For
   '     End If
  '      '<result property="d0001" column="d0001"/><!-- ±‡∫≈ -->
  '      Print #1, "        <result property=""" & fieldValue & """ column=""" & fieldValue & """/> <!-- " & itemValue & " -->"
  '  Next i
  '  Print #1, "    </resultMap>"
 '   Print #1, ""
    
    ' ‰≥ˆ±Ì◊÷∂Œ
    Print #1, "    <!-- ±Ì◊÷∂Œ–≈œ¢  -->"
    Print #1, "    <sql id='tableColumns'>"
     WK_Str = "        "
    For i = dataStartLine To dataEndLine     '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        ElseIf i <> dataStartLine Then
                WK_Str = WK_Str & "," & LCase(fieldValue)
        Else
            WK_Str = WK_Str & LCase(fieldValue)
        End If
    Next i
    
    Print #1, WK_Str
    Print #1, "    </sql>"
    Print #1, ""
    
    '≈˙¡ø≤Â»Î
    'Print #1, "    <!-- ≈˙¡ø≤Â»Î  -->"
    'Print #1, "    <insert id='doBatchInsert' parameterType=" & sTemp & ">"
    'Print #1, "        INSERT INTO " & LCase(sdbname) & " ( <include refid='tableColumns'/> ) VALUES "
    'Print #1, "            <foreach collection='list' item='item' separator=','>"
    
    'WK_Str = "                ("
    'For i = dataStartLine To dataEndLine     '—≠ª∑¥¶¿Ì
    '    If Not getEntityValue(i) Then
    '        Exit For
    '    ElseIf i <> dataStartLine Then
    '        WK_Str = WK_Str & "," & "#{item." & LCase(fieldValue) & "}"
    '    Else
    '        WK_Str = WK_Str & " " & "#{item." & LCase(fieldValue) & "}"
    '    End If
    'Next i
    'WK_Str = WK_Str & " )"
    
    'Print #1, WK_Str
    'Print #1, "            </foreach>"
    'Print #1, "    </insert>"
     '≈˙¡ø…æ≥˝
    'Print #1, "    <!-- ≈˙¡ø…æ≥˝  -->"
    'Print #1, "    <delete id='doBatchDelete' parameterType=" & sTemp & ">"
    'Print #1, "        DELETE FROM " & LCase(sdbname) & " WHERE puk IN "
    'Print #1, "            <foreach collection='list' item='item' open='(' separator=',' close=')'>"
    'Print #1, "                #{item.puk}"
    'Print #1, "            </foreach>"
    'Print #1, "    </delete>"
    'Print #1, ""
    
    '∏˘æ›Ãıº˛∏¸–¬À˘”–º«¬º
    Print #1, "    <!-- ∏˘æ›Ãıº˛∏¸–¬À˘”–º«¬º -->"
    Print #1, "    <update id='doUpdateAll' parameterType=" & sTemp & ">"
    Print #1, "        UPDATE " & LCase(sdbname) & " SET  uu1 = #{uuu} <!-- ∏¸–¬ ±º‰°¢∏¸–¬’ﬂ -->"
    For i = dataStartLine + 1 To dataEndLine    '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "Y" Then
            If LCase(fieldValue) <> "uu1" Then
                '<if test=°±state != null°±>
                Print #1, "        <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
                Print #1, "            ," & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
                Print #1, "        </if>"
            End If
        End If
    Next i
    Print #1, "        WHERE 1 = 1"
    For i = dataStartLine To dataEndLine     '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        '<if test=°±state != null°±>
        Print #1, "        <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
        Print #1, "            AND " & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
        Print #1, "        </if>"
    Next i
    Print #1, "    </update>"
    
    '∑÷“≥≤È—Ø–≈œ¢
    Print #1, "    <!-- ≤È—Ø∑÷“≥ ˝æ›–≈œ¢  -->"
    Print #1, "    <select id='doSelectPage' parameterType=" & sTemp & " resultType=" & sTemp & ">"
    Print #1, "        SELECT <include refid='tableColumns'/> FROM " & LCase(sdbname)
    Print #1, "        WHERE  1 = 1"
    For i = dataStartLine To dataEndLine     '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        '<if test=°±state != null°±>
        Print #1, "        <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
        Print #1, "            AND " & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
        Print #1, "        </if>"
    Next i
    Print #1, "    </select>"
    Print #1, ""
    
    '≤Â»Î–≈œ¢
    Print #1, "    <!-- ≤Â»Î“ªÃı ˝æ› -->"
    Print #1, "    <insert id='doInsert' parameterType=" & sTemp & ">"
    Print #1, "        INSERT INTO " & LCase(sdbname)
    Print #1, "          ( <include refid='tableColumns'/>) "
    Print #1, "        VALUES "
     WK_Str = "          ("
    For i = dataStartLine To dataEndLine     '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        ElseIf i <> dataStartLine Then
            WK_Str = WK_Str & "," & "#{" & LCase(fieldValue) & "}"
        Else
            WK_Str = WK_Str & " " & "#{" & LCase(fieldValue) & "}"
        End If
    Next i
    
    Print #1, WK_Str & " )"
    Print #1, "    </insert>"
    
    '–ﬁ∏ƒ–≈œ¢
    Print #1, "    <!-- –ﬁ∏ƒ“ªÃı ˝æ› -->"
    Print #1, "    <update id='doUpdate' parameterType=" & sTemp & ">"
    Print #1, "        UPDATE " & LCase(sdbname) & " SET uu1 = #{uuu} <!-- ∏¸–¬ ±º‰°¢∏¸–¬’ﬂ -->"
    For i = dataStartLine + 1 To dataEndLine    '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "Y" Then
            If LCase(fieldValue) <> "uu1" Then
                '<if test=°±state != null°±>
                Print #1, "        <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
                Print #1, "            ," & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
                Print #1, "        </if>"
            End If
        End If
    Next i
    
    Print #1, "        WHERE puk = #{puk}"

    For i = dataStartLine + 1 To dataEndLine    '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=°±state != null°±>
            Print #1, "            <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
            Print #1, "            </if>"
        End If
    Next i
    
   
    Print #1, "            <if test="" uu1 != null and uu1 !='' ""><!-- ◊Ó∫Û∏¸–¬ ±º‰ -->"
    Print #1, "                AND uu1 = #{uu1}"
    Print #1, "            </if>"
    
    Print #1, "    </update>"
    
     '…æ≥˝–≈œ¢
    Print #1, "    <!-- ¬ﬂº≠…æ≥˝“ªÃı ˝æ› -->"
    Print #1, "    <delete id='toDelete' parameterType=" & sTemp & ">"
    Print #1, "        UPDATE " & LCase(sdbname) & " SET ddd='1', uu1 = #{uuu}, uu2 = #{uu2} WHERE puk = #{puk}"
    
    For i = dataStartLine + 1 To dataEndLine    '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=°±state != null°±>
            Print #1, "            <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
            Print #1, "            </if>"
        End If
    Next i
    
   
    Print #1, "            <if test="" uu1 != null and uu1 !='' ""><!-- ◊Ó∫Û∏¸–¬ ±º‰ -->"
    Print #1, "                AND uu1 = #{uu1}"
    Print #1, "            </if>"
    
    Print #1, "    </delete>"
    
    '…æ≥˝–≈œ¢
    Print #1, "    <!-- ŒÔ¿Ì…æ≥˝“ªÃı ˝æ› -->"
    Print #1, "    <delete id='doDelete' parameterType=" & sTemp & ">"
    Print #1, "        DELETE FROM " & LCase(sdbname) & " WHERE puk = #{puk}"
    
    For i = dataStartLine + 1 To dataEndLine    '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=°±state != null°±>
            Print #1, "            <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
            Print #1, "            </if>"
        End If
    Next i
    
    Print #1, "            <if test="" uu1 != null and uu1 !='' ""><!-- ◊Ó∫Û∏¸–¬ ±º‰ -->"
    Print #1, "                AND uu1 = #{uu1}"
    Print #1, "            </if>"
    
    Print #1, "    </delete>"
    
    '≤È—Ø–≈œ¢
    Print #1, "    <!-- ≤È—Ø“ªÃı ˝æ›  -->"
    Print #1, "    <select id='doRead' parameterType=" & sTemp & " resultType=" & sTemp & ">"
    Print #1, "        SELECT <include refid='tableColumns'/> FROM " & LCase(sdbname) & " WHERE 1 = 1"
    
    For i = dataStartLine To dataEndLine    '—≠ª∑¥¶¿Ì
        If Not getEntityValue(i) Then
            Exit For
        End If
        If keyValue <> "" Then
            '<if test=°±state != null°±>
            Print #1, "            <if test="" " & LCase(fieldValue) & " != null and " & LCase(fieldValue) & " !='' ""><!-- " & itemValue & " -->"
            Print #1, "                AND " & LCase(fieldValue) & " = #{" & LCase(fieldValue) & "}"
            Print #1, "            </if>"
        End If
    Next i
    
    Print #1, "            <if test="" ddd != null and ddd !='' ""><!-- ◊Ó∫Û∏¸–¬ ±º‰ -->"
    Print #1, "                AND ddd = #{ddd}"
    Print #1, "            </if>"
    
    Print #1, "    </select>"
    Print #1, ""
    
    'Ω· ¯±Íº«
    Print #1, "</mapper>"
    Close #1
    
    MsgBox ("Œƒº˛◊ˆ≥…ÕÍ¡À")
    
    Exit Sub
    
Open_Error:
    MsgBox ("Œƒº˛◊ˆ≥… ß∞‹" & Chr(13) & _
        "«Î‘Ÿ¥Œ‘À––")
End Sub
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatBean()

    SYSNAME = Cells(1, 52).Value
    spgname = Cells(1, 31).Value
    sdbname = SYSNAME & Cells(1, 62).Value
    sver = Cells(4, 78).Value
       
    sfxdate = Format(Date, "YYYY/MM/DD")

    sfileName = Application.GetSaveAsFilename(InitialFilename:=sdbname & "", _
                          fileFilter:="java  , *.java")
    
    If sfileName = "" Or sfileName = "False" Then
        MsgBox ("File Name")
        Exit Sub
    End If
        
On Error GoTo Open_Error
    
    Const OBJ_EXT = ".JAVA"
    If UCase(Right(sfileName, Len(OBJ_EXT))) = OBJ_EXT Then
        sfileName = Left(sfileName, Len(sfileName) - Len(OBJ_EXT))
    End If
    
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    Open sfileName & "PVO" & ".java" For Output Shared As #1
    
    Print #1, "import javax.inject.Named;"
    Print #1, " "
    Print #1, "@Named"
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public class " & sdbname & "PVO extends " & sdbname & "DBO"
    
    Print #1, "{"
    Print #1, "}"
    
    Close #1
      
      
    Open sfileName & "DBO" & ".java" For Output Shared As #1
    
    Print #1, "import javax.inject.Named;"
    Print #1, " "
    Print #1, "@Named"
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public class " & sdbname & "DBO extends MyDataBaseObjectSupport"
    
    Print #1, "{"
    
    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
      
    Application.ScreenUpdating = False
            
    'field
    For i = dataStartLine + 1 To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If InStr(fieldValue, "BBB") > 0 Then
            Exit For
        End If
        '/**********œÓƒø…Ë÷√**********/
        Print #1, "    /** "
        Print #1, "     * " & itemValue
        Print #1, "     */"
            
        fieldValue = LCase(fieldValue)
            
        'ëÆ?
        'String
        WK_Str = "    private String " & (fieldValue) & " = """";"
            
        Print #1, WK_Str
        Print #1, " "
    Next i

    'get
    For i = dataStartLine + 1 To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If InStr(fieldValue, "BBB") > 0 Then
            Exit For
        End If
    
        '/**********œÓƒø…Ë÷√**********/
        Print #1, "    /** "
        Print #1, "     * ªÒ»°" & itemValue
        Print #1, "     *"
        Print #1, "     * @return " & fieldValue & " " & itemValue
        Print #1, "     */"
            
        fieldValue = LCase(fieldValue)
            
        'ëÆ?
        'String
        WK_Str = "    public String get" & upFirstStr(fieldValue) & "() {"
            
        Print #1, WK_Str
        
        'return
        WK_Str = "        return this." & fieldValue & ";"
        Print #1, WK_Str
        Print #1, "    }"
        Print #1, " "
    Next i
   
    'set
    For i = dataStartLine + 1 To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
    
        If InStr(fieldValue, "BBB") > 0 Then
            Exit For
        End If
    
        '/**********œÓƒø…Ë÷√**********/
        Print #1, "    /** "
        Print #1, "     * …Ë÷√" & itemValue
        Print #1, "     *"
        Print #1, "     * @param " & (fieldValue) & " " & itemValue
        Print #1, "     */"
            
            
        'fieldValue = LCase(fieldValue)
            
        'String
        WK_Str = "    public void set" & upFirstStr(LCase(fieldValue)) & "(String " & (fieldValue) & ") {"
        Print #1, WK_Str
        
        'return
        WK_Str = "        this." & LCase(fieldValue) & " = " & (fieldValue) & ";"
        Print #1, WK_Str
            
        'Other
        Print #1, "    }"
        Print #1, " "
    Next i
    
    Print #1, "}"
    
    Close #1
      
    'ª≠√Ê ¬º˛œÏ”¶£®µ•ª≠√Ê£©
    Call CreatController
    'œµÕ≥“µŒÒ¬ﬂº≠
    Call CreatService
    ' ˝æ›≤Ÿ◊˜ µÃÂ£®µ•±Ì£©
    Call CreatDao
    
    MsgBox ("OK")
    Exit Sub
    
Open_Error:
    MsgBox ("Error")
End Sub
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatController()
        
On Error GoTo Open_Error
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & "Controller.java" For Output Shared As #1
    
    Print #1, "import javax.annotation.Resource;"
    Print #1, ""
    
    Print #1, "import org.slf4j.Logger;"
    Print #1, "import org.slf4j.LoggerFactory;"
    Print #1, "import org.springframework.stereotype.Controller;"
        
    Print #1, ""
    Print #1, "@Controller"
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public class " & sdbname & "Controller extends MyControllerSupport {"
    Print #1, "    protected static final Logger logger = LoggerFactory.getLogger(" & sdbname & "Controller.class);"
    Print #1, "    @Resource"
    Print #1, "    protected " & sdbname & "Service " & sdbname & "Service_;"
    Print #1, ""
    Print #1, "    public MyModelAndViewSupport getModelAndView(){"
    Print #1, "        return new MyModelAndViewSupport(""" & sdbname & """);"
    Print #1, "    }"
    Print #1, ""
    Print #1, "}"
        
    Close #1
    
    Exit Sub
Open_Error:
    MsgBox ("Œƒº˛◊ˆ≥… ß∞‹" & Chr(13) & _
        "«Î‘Ÿ¥Œ‘À––")
End Sub
Sub CreatService()
        
On Error GoTo Open_Error
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & "Service.java" For Output Shared As #1
    
    Print #1, "import org.springframework.stereotype.Service;"
    Print #1, "import org.slf4j.Logger;"
    Print #1, "import org.slf4j.LoggerFactory;"
    Print #1, ""
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "@Service"
    Print #1, "public class " & sdbname & "Service extends MyServiceSupport {"
    Print #1, "    protected static final Logger logger = LoggerFactory.getLogger(" & sdbname & "Service.class);"
    Print #1, ""
    Print #1, "    public " & sdbname & "Dao getDao(){"
    Print #1, "        return getMySqlSession().getMapper(" & sdbname & "Dao.class);"
    Print #1, "    }"
    Print #1, ""
    Print #1, "}"
        
    Close #1
    
    Exit Sub
Open_Error:
    MsgBox ("Œƒº˛◊ˆ≥… ß∞‹" & Chr(13) & _
        "«Î‘Ÿ¥Œ‘À––")
End Sub
Sub CreatDao()
        
On Error GoTo Open_Error
    
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    Open sfileName & "Dao.java" For Output Shared As #1

    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public interface " & sdbname & "Dao extends ISDatabaseSupport{"
    Print #1, ""
    Print #1, "}"
        
    Close #1
    
    Exit Sub
Open_Error:
    MsgBox ("Œƒº˛◊ˆ≥… ß∞‹" & Chr(13) & _
        "«Î‘Ÿ¥Œ‘À––")
End Sub
''''''''''''''''''''''''
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Sub CreatEntity()

     SYSNAME = Cells(1, 52).Value
    spgname = Cells(1, 31).Value
    sdbname = SYSNAME & Cells(1, 62).Value
    sver = Cells(4, 78).Value
       
    sfxdate = Format(Date, "YYYY/MM/DD")

    sfileName = Application.GetSaveAsFilename(InitialFilename:=sdbname & "", _
                          fileFilter:="java  , *.java")
    
    If sfileName = "" Or sfileName = "False" Then
        MsgBox ("File Name")
        Exit Sub
    End If
        
On Error GoTo Open_Error
    
    Const OBJ_EXT = ".JAVA"
    If UCase(Right(sfileName, Len(OBJ_EXT))) = OBJ_EXT Then
        sfileName = Left(sfileName, Len(sfileName) - Len(OBJ_EXT))
    End If
    
    Dim sKeyName As String
    Dim index As Integer
    
    sKeyName = sfileName
    index = InStr(sKeyName, "\")
    While index > 0
        sKeyName = Mid(sKeyName, index + 1)
        index = InStr(sKeyName, "\")
    Wend
    
    Open sfileName & "ET" & ".java" For Output Shared As #1
    
    '-------------------------------
    ' Õ∑◊¢ Õ
    '-------------------------------
    'Print #1, "/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
    'Print #1, " * System Name        : " & spgname
    'Print #1, " * File Name          : " & sKeyName & ".java"
    'Print #1, " * Compiler           : JDK1.6"
    'Print #1, " *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/"
    Print #1, "package org.jfp.business.;"
    Print #1, " "
    Print #1, "import javax.inject.Named;"
    Print #1, "import org.jfp.framework.support.MyDaoSupport;"
    Print #1, " "
    Print #1, "@Named"
    Print #1, "/** " & Trim(Cells(1, 31).Value) & "*/"
    Print #1, "public class " & sdbname & "DO extends MyDaoSupport"
    
    Print #1, "{"

    Dim WK_Str      As String
    Dim i           As Integer
    Dim strKeyName      As String
      
    Application.ScreenUpdating = False
        
    'field
    For i = dataStartLine + 1 To dataEndLine
        WK_Str = ""
        If Not getEntityValue(i) Then
            Exit For
        End If
        '/**********œÓƒø…Ë÷√**********/
        'Print #1, "    /** "
        'Print #1, "     * " & itemValue
        'Print #1, "     */"
            
        fieldValue = LCase(fieldValue)
            
        'ëÆê 
        'String
        WK_Str = "    public String " & (fieldValue) & " { get; set; }"
            
        Print #1, WK_Str
        Print #1, " "
    Next i

    Print #1, "}"
    
    Close #1
    
    MsgBox ("OK")
    Exit Sub
    
Open_Error:
    MsgBox ("Error")
End Sub


