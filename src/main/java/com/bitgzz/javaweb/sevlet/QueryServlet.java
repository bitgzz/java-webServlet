package com.bitgzz.javaweb.sevlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: bitgzz
 * Created: 2018/7/28
 */
//数据查询
public class QueryServlet extends HttpServlet {
    //城市
    private Map<String, String> cityMap = new HashMap<String, String>();
    //城市里面的景点
    private Map<String, List<String>> scenicSpot = new HashMap<String, List<String>>();

    @Override
    public void init() throws ServletException {
        super.init();
        
        List<String> xian = new ArrayList<String>();
        xian.add("华清池");
        xian.add("兵马俑");
        xian.add("大雁塔");
        scenicSpot.put("xian", xian);
        cityMap.put("xian", "西安");
        
        List<String> baoJi = new ArrayList<String>();
        baoJi.add("太白山");
        baoJi.add("法门寺");
        baoJi.add("关山牧场");
        scenicSpot.put("baoJi", baoJi);
        cityMap.put("baoJi", "宝鸡");
        
        List<String> xianyang = new ArrayList<String>();
        xianyang.add("乾陵");
        xianyang.add("袁家村");
        scenicSpot.put("xianyang", xianyang);
        cityMap.put("xianyang", "咸阳");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //处理参数
        String city = req.getParameter("city");
        //准备数据
        List<ScenicsDto> scenicsDtoArrayList = new ArrayList<ScenicsDto>();
        if (city == null || city.length() == 0) {
            //全部
            for (Map.Entry<String, List<String>> entry : scenicSpot.entrySet()) {
                String cityKey = entry.getKey();
                List<String> scenics = entry.getValue();
                //遍历景点
                for (String item : scenics) {
                    ScenicsDto scenicsDto = new ScenicsDto();
                    //返回景点所对应的城市
                    scenicsDto.setCity(cityMap.get(cityKey));
                    scenicsDto.setName(item);
                    scenicsDtoArrayList.add(scenicsDto);
                }
            }
        } else {
            //单个城市
            List<String> scenics = scenicSpot.get(city);
            if (scenics == null) {
                scenics = new ArrayList<String>();
            }
            for (String item : scenics) {
                ScenicsDto scenicsDto = new ScenicsDto();
                scenicsDto.setCity(cityMap.get(city));
                scenicsDto.setName(item);
                scenicsDtoArrayList.add(scenicsDto);
            }
        }
        
        //响应数据
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>景点</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <td>编号</td>\n" +
                "        <td>所在城市</td>\n" +
                "        <td>景点名称</td>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody>")
        ;
        
        int id = 1;
        for (ScenicsDto dto : scenicsDtoArrayList) {
            writer.append("<tr>")
                    .append("<td>").append(String.valueOf(id)).append("</td>")
                    .append("<td>").append(dto.city).append("</td>")
                    .append("<td>").append(dto.scenicSpotname).append("</td>")
                    .append("</tr>");
            id = id + 1;
        }
        writer.append("    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");
        
    }


    
    public static class ScenicsDto {
        private String city;//城市
        private String scenicSpotname;//城市中的景点名字
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
        
        public String getName() {
            return scenicSpotname;
        }
        
        public void setName(String name) {
            this.scenicSpotname = name;
        }
    }
}
