function openDate(year,month,day,callback){
    uexChooseDate.openDatePicker(year,month,day);
    uexChooseDate.cbOpenDatePicker=function(opCode,dataType,data){
        if(dataType==1){
            data = eval("("+data+")");
            if(data.month<10) data.month = "0" + data.month;
            if(data.day<10) data.day = "0" + data.day;
            var json = {
                year : data.year,
                month : data.month,
                day : data.day,
            };
            callback(json);
        }
    }   
};
