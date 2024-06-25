package com.example.interceptor_middleware02.interceptors;

import com.example.interceptor_middleware02.entity.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "Gennhaionen"),
            new Month(2, "February", "Febbraio", "Februarioen"),
            new Month(3, "March", "Marzo", "Marzonen"),
            new Month(4, "April", "Aprile", "Aprillen"),
            new Month(5, "May", "Maggio", "Maggioneenn"),
            new Month(6, "June", "Giugno", "Juneen")));

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURL().toString().contains("/month")) {
            String monthNumberString = request.getHeader("monthNumber");
            if (monthNumberString == null || monthNumberString.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return true;
            }
            long monthNumber = Long.parseLong(monthNumberString);
            Optional<Month> month = months.stream().filter(singlMonth -> {
                return singlMonth.getMonthNumber() == monthNumber;
            }).findFirst();
            if (month.isEmpty()) {
                Month emptyMonth = new Month();
                emptyMonth.setEnglishName("none");
                emptyMonth.setGermanName("none");
                emptyMonth.setItalianName("none");
                request.setAttribute("MonthInterceptor-month", emptyMonth);
                response.setStatus(HttpServletResponse.SC_OK);
                return true;
            }
            month.ifPresent(value -> request.setAttribute("MonthInterceptor-month", value));
            return true;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
