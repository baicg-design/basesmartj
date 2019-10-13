
//生成SQL_NO
function getSeqNo(){
    var d = new Date();
    var curr_date = d.getDate();
    var curr_month = d.getMonth() + 1;
    var curr_year = d.getFullYear();
    String(curr_month).length < 2 ? (curr_month = "0" + curr_month): curr_month;
    String(curr_date).length < 2 ? (curr_date = "0" + curr_date): curr_date;
    var yyyyMMdd = curr_year + "" + curr_month +""+ curr_date;
    return yyyyMMdd + (new Date()).getTime();
}

//生成Source_type,暂时不封装
function getSourceType() {
    return "FT";
}

//生成日期
function getDate() {
    var d = new Date();
    var curr_date = d.getDate();
    var curr_month = d.getMonth() + 1;
    var curr_year = d.getFullYear();
    String(curr_month).length < 2 ? (curr_month = "0" + curr_month): curr_month;
    String(curr_date).length < 2 ? (curr_date = "0" + curr_date): curr_date;
    var yyyyMMdd = curr_year + "" + curr_month +""+ curr_date;
    return yyyyMMdd;
}

//生成时间戳YYYYMMDD:HHMMSS二十四小时制
function gettranTimeStamp() {

    var date = new Date();

    var str=""
    str+=date.getFullYear()//年
    str+=date.getMonth()+1//月 月比实际月份要少1
    str+=date.getDate()//日
    str+=date.getHours()//HH
    str+=date.getMinutes()//MM
    str+=date.getSeconds() //SS
    return str;

}

//当前页数
function getPageIndex() {
    return "1";
}

//每页显示的条数
function getPageSize() {
    return "10";
}
//生成系统头
function getHeader() {
    //定义一个数组
    var head = {"seqNo":getSeqNo(),"sourceType":getSourceType(),"tranDate":getDate(),"tranTimeStamp":gettranTimeStamp(),"pageIndex":getPageIndex(),"pageSize":getPageSize()}; //创建对象；

    return JSON.stringify(head);

}