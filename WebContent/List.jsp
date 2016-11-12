
<%@page import="frs_cls.JdbcConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%
       /*//try {
              String s[] = null;

              JdbcConnect jc=new JdbcConnect();
              Connection con = jc.getConnection();
              Statement st = con.createStatement();
              ResultSet rs = null;
              rs = st.executeQuery("select distinct huma_id from huma_mstr");

              List li = new ArrayList();

              while (rs.next()) {
                     li.add(rs.getString(2));
              }

              String[] str = new String[li.size()];
              Iterator it = li.iterator();

              int i = 0;
              while (it.hasNext()) {
                     String p = (String) it.next();
                     str[i] = p;
                     i++;
              }

              //jQuery related start
              String query = (String) request.getParameter("q");

              int cnt = 1;
              for (int j = 0; j < str.length; j++) {
                     if (str[j].toUpperCase().startsWith(query.toUpperCase())) {
                           out.print(str[j] + "\n");
                           if (cnt >= 5)// 5=How many results have to show while we are typing(auto suggestions)
                                  break;
                           cnt++;
                     }
              }
              //jQuery related end

              rs.close();
              st.close();
              con.close();

       } catch (Exception e) {
              e.printStackTrace();
       }
*/
       //http://corejavaexample.blogspot.in/
    String countries[] = {
                            "Afghanistan",
                            "Albania",
                            "Algeria",
                            "Andorra",
                            "Angola",
                            "Antigua and Barbuda",
                            "Argentina",
                            "Armenia",
                            "Yemen",
                            "Zambia",
                            "Zimbabwe"
                            };
 
    String query = (String)request.getParameter("q");
    //System.out.println("1"+request.getParameterNames().nextElement());
    response.setHeader("Content-Type", "text/html");
    int cnt=1;
    for(int i=0;i<countries.length;i++)
    {
        if(countries[i].toUpperCase().startsWith(query.toUpperCase()))
        {
            out.print(countries[i]+"\n");
            if(cnt>=10)
                break;
            cnt++;
        }
    }
%>
