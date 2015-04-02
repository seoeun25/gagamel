package com.seoeun.sort;

public class PolyList {

    public static void main(String[] ar) {

        List A = new List();
        List B = new List();
        List C = new List();
        /**
         * 메인문 작성 : (3점)
         * A : 4x^5+2x^3+10x^2+1x^0
         * B : 3x^4-2x^3+2x^2-4x^1
         * C : 결과>
         */
        A.appendTerm(4,5);
        A.appendTerm(2,3);
        A.appendTerm(-10,2);
        A.appendTerm(1,0);

        B.appendTerm(3,4);
        B.appendTerm(-2,3);
        B.appendTerm(2,2);
        B.appendTerm(-4,1);


        C = addPoly(A, B);
        // 1. 위의 A와 B에 해당하는 다항식을 appendTerm 메서드를 이용하여 해당 리스트에 삽입
        // 2. addPoly 메서드를 이용하여 리스트 C에 연산 결과 리스트를 저장
        // 3. C의 다항식을 결과화면과 같이 출력한다. (계수가 양수일 경우 +, 음수일 경우 -로 표시. 최고 차항의 계수는 따로 처리하지 않는다.)


        System.out.print("C : ");
        C.print(); // a+b인 c 출력


    }

    /**
     * 다항식 LIST A와 B의 합 C를 반환하는 메소드 교재 알고리즘 6-14(p252)를 참고하여 작성하시오 (4점)
     *
     * @param A
     * @param B
     * @return
     */
    public static List addPoly(List A, List B){
        List C = new List();

        Node p = A.getPL();
        Node q = B.getPL();
        //Node r = C.getPL();
        float sum=0;
        while(p!=null && q!=null /* 조건문 작성 */ ){

            if(p.getExpo() == q.getExpo()){
                sum = p.coef + q.coef;

                if(sum != 0){
                    C.appendTerm(sum, q.expo);
                }
                p = p.link;
                q = q.link;
                //작성
            }
            else if(p.getExpo() < q.getExpo()){
                C.appendTerm(q.coef, q.expo);
                q = q.link;
                //작성
            }
            else{
                C.appendTerm(p.coef, p.expo);
                p=p.link;
                //작성
            }
        }
        while(p != null){
            //작성
            C.appendTerm(p.coef, p.expo);
            p=p.link;
        }

        while(q != null){
            C.appendTerm(q.coef, q.expo);
            q=q.link;
            //작성
        }
        return C;
    }

    public static class List {
        private Node PL, last;

        List() {
            PL = null;
            last = null;
        }

        /**
         * 리스트에 다항식을 추가하는 메서드 교재 알고리즘 6-13(p247) 을 참고하여 작성하시오 (3점)
         *
         */
        public void print() {
            Node node = PL;

            while (node != null) {
                if (node.getCoef() > 0)
                {
                    System.out.print("+");
                }
                System.out.print(node.getCoef());

                System.out.print("x^" + node.getExpo());

                node = node.getLink();
            }
            System.out.println();
        }
        public void appendTerm(float coef, int expo) {	//다항식에서의 항 삽입 연산

            Node node = new Node();

            node.setCoef(coef);
            node.setExpo(expo);
            node.setLink(null);
            if(PL==null){
                PL= node;
                last=node;
            }
            else{
                last.link = node;
                last = node;
            }
            // 작성 하시오
        }
        public Node getPL() {
            return PL;
        }
        public void setPL(Node pL) {
            PL = pL;
        }
        public Node getLast() {
            return last;
        }
        public void setLast(Node last) {
            this.last = last;
        }
    }
    public static class Node {
        float coef;
        int expo;
        Node link;

        Node() {
            this.link = null;
        }

        Node(float coef, int expo) {
            this.coef = coef;
            this.expo = expo;

        }

        Node(float coef, int expo, Node link) {
            this.coef = coef;
            this.expo = expo;
            this.link = link;
        }

        public float getCoef() {
            return coef;
        }

        public void setCoef(float coef) {
            this.coef = coef;
        }

        public int getExpo() {
            return expo;
        }

        public void setExpo(int expo) {
            this.expo = expo;
        }

        public Node getLink() {
            return link;
        }

        public void setLink(Node link) {
            this.link = link;
        }

    }
}
