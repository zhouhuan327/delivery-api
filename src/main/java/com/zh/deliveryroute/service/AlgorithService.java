package com.zh.deliveryroute.service;

import Code_Fina.*;
import com.mathworks.toolbox.javabuilder.MWCellArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.zh.deliveryroute.domain.CoNode;
import com.zh.deliveryroute.domain.NodeData;
import com.zh.deliveryroute.domain.Result;
import com.zh.deliveryroute.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AlgorithService {
    @Autowired
    NodeRepository nodeRepository;
    /*初值
     * n
     * m
     * q
     * maxroude
     * sizepop
     * maxgn =
     *
     * */
    public Result getRouteByDis(NodeData nodeData,int n,int maxroude,int[] maxCarring){
        List<double[]> distance = nodeData.getDisData();
        double[] qt = nodeData.getQtdata();

        double [][]distanceArray = new double[distance.size()][];
        double [][]qtArray = new double[qt.length][];
        for(int i =0;i<distance.size();i++){
            distanceArray[i] = distance.get(i);
        }
        for(int i =0;i<qt.length;i++){
            double temp[] = {qt[i]};
            qtArray[i] = temp;
        }
        System.out.println("Start by Distance");
        Object[]result = new Object[0];
        try {
            CeShi_1 distanceClass = new CeShi_1();

            //坐标数据、供货量、客户数n、里程上限maxroude、各车型载重上限upCarring
            result = distanceClass.Code_CeShi_1(4,distanceArray,qtArray,n,maxroude,maxCarring);
            System.out.println(result);
            MWNumericArray data1 = (MWNumericArray) result[0];
            double[][] lj = (double[][])data1.toArray();
            MWNumericArray data2 = (MWNumericArray) result[1];
            double[][] lc = (double[][])data2.toArray();
            MWNumericArray data3 = (MWNumericArray) result[2];
            double[][] zz = (double[][])data3.toArray();

            MWNumericArray data4 = (MWNumericArray) result[3];
            Object[] xh = data4.toArray();
            // 路径信息、每辆车里程、每辆车载重、各车载重型号
            double resultRoute[] = lj[0];
            double resultDistance[] = lc[0];
            double resultWeight[] = zz[0];
            Object resultWeightType[] = xh;

            Result result1 = new Result();
            result1.setRoute(resultRoute);
            result1.setDistance(resultDistance);
            result1.setWeight(resultWeight);
            result1.setWeightType(resultWeightType);

            System.out.println("距离的");
            System.out.println("路线"+Arrays.toString(resultRoute));
            System.out.println("距离"+Arrays.toString(resultDistance));
            System.out.println("载重"+Arrays.toString(resultWeight));
            System.out.println("种类"+Arrays.toString(resultWeightType));
            return result1;
//            MWNumericArray a = null;
//            a = new MWNumericArray(Double.valueOf(10), MWClassID.DOUBLE);
        } catch (MWException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*初值
     * n =20 客户数
     * m = 5 车数量
     * q = 8
     * maxroude = 50 最大里程限制
     * sizepop 100
     * maxgn = 1000
     *
     * */
    public Result getRoteByCoo(NodeData nodeData,int n,int maxroude,int[] maxCarring){
        List<CoNode> coodata = nodeData.getCooData();
        double[][] cooArray = new double[coodata.size()][];
        double[][] qtArray = new double[coodata.size()-1][];
        for(int i =0;i<coodata.size();i++){
            double temp[] = {coodata.get(i).getX(),coodata.get(i).getY()};
            cooArray[i] = temp;
        }
        for(int i =1;i<coodata.size();i++){
            double temp2[] = {coodata.get(i).getQt()};
            qtArray[i-1] = temp2;
        }
        System.out.println("Start by Coordinate");
        Object[]result = new Object[0];
        try {
            CeShi_2 coodinateClass = new CeShi_2();
            //坐标数据、供货量、客户数n、里程上限maxroude、各车型载重上限upCarring
            result = coodinateClass.Code_CeShi_2(4,cooArray,qtArray,n,maxroude,maxCarring);
            System.out.println(result);
            MWNumericArray data1 = (MWNumericArray) result[0];
            double[][] lj = (double[][])data1.toArray();
            MWNumericArray data2 = (MWNumericArray) result[1];
            double[][] lc = (double[][])data2.toArray();
            MWNumericArray data3 = (MWNumericArray) result[2];
            double[][] zz = (double[][])data3.toArray();
            MWNumericArray data4 = (MWNumericArray) result[3];
            Object[] xh = data4.toArray();
            double resultRoute[] = lj[0];
            double resultDistance[] = lc[0];
            double resultWeight[] = zz[0];
            Object resultWeightType[] = xh;

            Result result1 = new Result();
            result1.setRoute(resultRoute);
            result1.setDistance(resultDistance);
            result1.setWeight(resultWeight);
            result1.setWeightType(resultWeightType);
            System.out.println("坐标的");
            System.out.println("路线"+Arrays.toString(resultRoute));
            System.out.println("距离"+Arrays.toString(resultDistance));
            System.out.println("载重"+Arrays.toString(resultWeight));
            System.out.println("种类"+Arrays.toString(resultWeightType));
            return  result1;
        } catch (MWException e) {
            e.printStackTrace();
        }
        return null;
    }

    //客户数、载重上限、里程上限
    public Result getrouteByRound(int m,int q,int maxroad){
        System.out.println("Start by Random");
        Object[]result = new Object[0];
        try {
            Rand rand = new Rand();
            //坐标数据、供货量、客户数n、载重上限q、里程上限maxroude
            result = rand.Code_Rand(3,m,q,maxroad);
            System.out.println(result);
            MWNumericArray data1 = (MWNumericArray) result[0];
            double[][] lj = (double[][])data1.toArray();
            MWNumericArray data2 = (MWNumericArray) result[1];
            double[][] lc = (double[][])data2.toArray();
            MWNumericArray data3 = (MWNumericArray) result[2];
            double[][] zz = (double[][])data3.toArray();
            double resultRoute[] = lj[0];
            double resultDistance[] = lc[0];
            double resultWeight[] = zz[0];

            Result result1 = new Result();
            result1.setRoute(resultRoute);
            result1.setDistance(resultDistance);
            result1.setWeight(resultWeight);
            System.out.println("坐标的");
            System.out.println("路线"+Arrays.toString(resultRoute));
            System.out.println("距离"+Arrays.toString(resultDistance));
            System.out.println("载重"+Arrays.toString(resultWeight));
            return  result1;
        } catch (MWException e) {
            e.printStackTrace();
        }
        return null;
    }
}
