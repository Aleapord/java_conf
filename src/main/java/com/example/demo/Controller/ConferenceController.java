package com.example.demo.Controller;

import com.example.demo.Pojo.Conf_user;
import com.example.demo.Pojo.Conference;
import com.example.demo.Services.ConferenceServices;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("loggedin/")
public class ConferenceController {

    @Autowired
    private ConferenceServices conferenceServices;

    @RequestMapping("getConf/")
    @ResponseBody
    public Conference getConf(int confid){
        return conferenceServices.selectConference(confid);
    }

    @RequestMapping("getAllConf/")
    @ResponseBody
    public List<Conference> getAllConf(){
        return conferenceServices.selectAllConference();
    }

    @RequestMapping("exportExcel")
    public void exportExcel(int confid, HttpServletResponse response) throws IOException {
        Conference conference = conferenceServices.selectConference(confid);
        String[] header={"会议名","会议介绍","会议地点","会议宾馆","会议时间","会议人物"};
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet=hssfWorkbook.createSheet(conference.getConfname());
        HSSFRow headRow =hssfSheet.createRow(0);
        for (int i=0;i<header.length;i++) {
            HSSFCell cell = headRow.createCell(i);
            HSSFRichTextString textString = new HSSFRichTextString(header[i]);
            cell.setCellValue(textString);
        }
        HSSFRow row1=hssfSheet.createRow(1);
        HSSFCell cell = row1.createCell(0);
        HSSFRichTextString textString = new HSSFRichTextString(conference.getConfname());
        cell.setCellValue(textString);
        cell = row1.createCell(1);
        textString = new HSSFRichTextString(conference.getConfinf());
        cell.setCellValue(textString);
        cell = row1.createCell(2);
        textString = new HSSFRichTextString(conference.getConfplace());
        cell.setCellValue(textString);
        cell = row1.createCell(3);
        textString = new HSSFRichTextString(conference.getConfhotle());
        cell.setCellValue(textString);
        cell = row1.createCell(4);
        textString = new HSSFRichTextString(conference.getConftime().toString());
        cell.setCellValue(textString);
        cell = row1.createCell(5);
        textString = new HSSFRichTextString(conference.getConfimg());
        cell.setCellValue(textString);

        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename= "+conference.getConfname()+".xls");


        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        hssfWorkbook.write(response.getOutputStream());
    }
    @RequestMapping("baoming/")
    @ResponseBody
    public HashMap<String,Object> baoming(@RequestBody Conf_user confUser){
        String s="";
        System.out.println(confUser.getName());
        s="success!";
        HashMap<String,Object> map = new HashMap<>();
        map.put("msg",s);
        return map;
    }
}
