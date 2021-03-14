package enumtype.designPatterns;

import generics.Generator;

/**
 * @ClassName VendingMachine
 * @Author bin
 * @Date 2020/8/4 下午7:00
 * @Decr TODO
 *      状态机- 通过enum，switch 不断变化状态。
 * @Link TODO
 **/
public class VendingMachine {
    //状态
    private static State state = State.RESTING;
    private static int amount = 0;

    //商品
    private static Input selection = null;

    //标记使用
    enum StateDuration {TRANSIENT;} //

    enum State {
        //休息
        RESTING {
            void next(Input input){
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDINT_MONEY;
                        break;
                    case SHOT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        //加钱
        ADDINT_MONEY {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection =input;
                        if(amount < selection.amount()) {
                            System.out.println("钱不够买: " + selection);
                        }else {
                            state = DISPENSING;
                        }
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHOT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }

        },
        //分配
        DISPENSING(StateDuration.TRANSIENT) {
            void next(){
                System.out.println("这是你选择的商品 "+selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        //给予改变
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next(){
                if(amount > 0) {
                    System.out.println("你的金额变化 " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        //终点
        TERMINAL {
            void output(){
                System.out.println("停止");
            }
        };

        private boolean isTransient = false;
        State(){};
        State(StateDuration stateDuration){
            isTransient = true;
        }

        void next(Input input){
            throw new RuntimeException("仅支持input的参数");
        }
        void next(){
            throw new RuntimeException("仅支持的TRANSIENT 参数");
        }
        void output(){
            System.out.println(amount);
        }
    }

    static void run(Generator<Input> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient){
                state.next();
            }
            state.output();
        }
    }


    static class RanomInputGenerator implements Generator<Input> {
       @Override
        public Input next() {
            return Input.randomSelection();
        }
    }

    public static void main(String[] args) {
        Generator<Input> gen =  new RanomInputGenerator();
        run(gen);

    }
}
