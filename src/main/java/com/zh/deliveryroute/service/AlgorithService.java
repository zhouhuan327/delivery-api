package com.zh.deliveryroute.service;

import Test.*;
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
    public Result getRouteByDis(NodeData nodeData,int n,int m,int q,int maxroude,int sizepop,int maxgen){
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
            DistanceClass distanceClass = new DistanceClass();
            result = distanceClass.Test_Distance(3,distanceArray,qtArray,n,m,q,maxroude,sizepop,maxgen);
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

            System.out.println("距离的");
            System.out.println("路线"+Arrays.toString(resultRoute));
            System.out.println("距离"+Arrays.toString(resultDistance));
            System.out.println("载重"+Arrays.toString(resultWeight));
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
    public Result getRoteByCoo(NodeData nodeData,int n,int m,int q,int maxroude,int sizepop,int maxgen){
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
            CoodinateClass coodinateClass = new CoodinateClass();
            result = coodinateClass.Test_Coodinate(3,cooArray,qtArray,n,m,q,maxroude,sizepop,maxgen);
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


    public void getroute2(){
        Optional<NodeData> obj = nodeRepository.findById(6L);
        List<CoNode> coodata = obj.get().getCooData();
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
        Object[]result = new Object[0];
        try {
            DataClass c =new DataClass();
            MWNumericArray a = null;
            a = new MWNumericArray(Double.valueOf(10), MWClassID.DOUBLE);
            result = c.Test_Data(3,100,20,10,100,500,30000);
//            result = c.Test_Coodinate(1,cooArray,qtArray,20,5,8,50,100,1000);
            System.out.println(result);
            MWCellArray cellArray = (MWCellArray) result[0];
           MWNumericArray d1 = (MWNumericArray)cellArray.getCell(1);
            MWNumericArray d2 = (MWNumericArray)cellArray.getCell(2);
            MWNumericArray d3 = (MWNumericArray)cellArray.getCell(3);
            double[][] d11 = (double[][]) d1.toDoubleArray();
           int test = 0;
        } catch (MWException e) {
            e.printStackTrace();
        }
    }
}
