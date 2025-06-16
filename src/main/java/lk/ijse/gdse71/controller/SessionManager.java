package lk.ijse.gdse71.controller;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * --------------------------------------------
 * Author: Zeenathul Ilma
 * GitHub: https://github.com/Seenathul-Ilma
 * Website: https://zeenathulilma.vercel.app/
 * --------------------------------------------
 * Created: 6/16/2025 10:49 PM
 * Project: CMS
 * --------------------------------------------
 **/

public class SessionManager {
    public static final Map<String, HttpSession> activeSessions = new ConcurrentHashMap<>();

}
