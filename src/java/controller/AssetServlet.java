/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Asset;
import services.AssetService;

/**
 *
 * @author RubySenpaii
 */
public class AssetServlet extends BaseServlet {
    
    private AssetService assetService;

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Add":
                    url = "/forms/asset/add.jsp";
                    break;
                case "Submit":
                    url = SubmitAsset(request);
                    break;
                case "List":
                default:
                    url = ListAssets(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    private String SubmitAsset(HttpServletRequest request) {
        assetService = new AssetService();
        Asset asset = new Asset();
        asset.AssetId = assetService.GetAssets().size() + 1;
        asset.AssetName = request.getParameter("asset-name");
        asset.AssetType = request.getParameter("asset-type");
        asset.Description = request.getParameter("description");
        asset.FundCluster = request.getParameter("fund-cluster");
        asset.StockNo = request.getParameter("stock-no");
        asset.Unit = request.getParameter("unit");
        int result = assetService.AddAsset(asset);
        if (result == 1) {
            return "/AssetServlet/List";
        } else {
            return "/AssetServlet/Add";
        }
    }
    
    private String ListAssets(HttpServletRequest request) {
        assetService = new AssetService();
        ArrayList<Asset> assets = assetService.GetAssets();
        HttpSession session = request.getSession();
        session.setAttribute("assets", assets);
        return "/forms/asset/list.jsp";
    }
}
