# android

캡스톤 대회 프로젝트
- 안드로이드를 사용, 개발 언어는 JAVA로 하였습니다
- 유아용 코딩어플 제작을 위해 게임을 만들었다.
- 기능은 크게 3가지
1) write
2) word matching
3) coding game

1. write (알파벳 따라 그리기)
-> 이벤트 처리용 함수를 이용 
public class DrawLine extends View
{
    //현재 그리기 조건(색상, 굵기, 등등.)을 기억 하는 변수.
    private Paint paint = null;

    //그리기를 할 bitmap 객체. -- 도화지라고 생각하면됨.
    private Bitmap bitmap = null;

    //bitmap 객체의 canvas 객체. 실제로 그리기를 하기 위한 객체.. -- 붓이라고 생각하면됨.
    private Canvas  canvas = null;

    //마우스 포인터(손가락)이 이동하는 경로 객체.
    private Path    path;

    //마우스 포인터(손가락)이 가장 마지막에 위치한 x좌표값 기억용 변수.
    private float   oldX;

    //마우스 포인터(손가락)이 가장 마지막에 위치한 y좌표값 기억용 변수.
    private float   oldY;

    //도형의 이동경로를 저장하는 객체
    private Path alphabet;

    //알파벳 포인트
    private Point[] alphabet_point = new Point[3];

    //비교 포인트
    private Point[] point = new Point[2];

    //알파벳 배열 움직일 i , 비교 배열 움직일 j
    private int i = 0;
    private int j = 0;
    
    /**
     * 생성자.. new DrawLine(this, rect) 하면 여기가 호출됨.
     * @param context   Context객체
     * @param rect      그리기 범위 화면 사이즈
     */
    public DrawLine(Context context, Rect rect)
    {
        this(context);
        int x = rect.width();
        int y = rect.height();

        //그리기를 할 bitmap 객체 생성.
        bitmap = Bitmap.createBitmap(rect.width(), rect.height(),
                Bitmap.Config.ARGB_8888);
        //그리기 bitmap에서 canvas를 알아옴.
        canvas = new Canvas(bitmap);

        //경로 초기화.
        path = new Path();
        alphabet = new Path();

        //알파벳 초기화
        drawTriangle(x/2 , y/2);
    }

    public DrawLine(Context context)
    {
        super(context);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        //앱 종료시 그리기 bitmap 초기화 시킴...
        if(bitmap!= null) bitmap.recycle();
        bitmap = null;

        super.onDetachedFromWindow();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        //그리기 bitmap이 있으면 현재 화면에 bitmap을 그린다.
        //자바의 view는 onDraw할때 마다 화면을 싹 지우고 다시 그리게 됨.
        if(bitmap != null)
        {
            canvas.drawBitmap(bitmap, 0, 0, null);

        }
    }

    //이벤트 처리용 함수..
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
            {
                //최초 마우스를 눌렀을때(손가락을 댓을때) 경로를 초기화 시킨다.
                path.reset();

                //그다음.. 현재 경로로 경로를 이동 시킨다.
                path.moveTo(x, y);

                //포인터 위치값을 기억한다.
                oldX = x;
                oldY = y;

                //알파벳의 시작 포인트(점) 과 터치시 시작점이 일치하는가?
                if(expo(alphabet_point[i].x,(int)oldX,alphabet_point[i].y,(int)oldY,70)){
                    Toast.makeText(getContext(), i+"번째점 완료", Toast.LENGTH_SHORT).show();
                    i++;
                    //알파벳의 시작 포인트의 배열을 3개로 줬기때문에 i=3이되면 배열주소를 초과하므로 초기화시켜줌)
                    if(i>2) i = 0;
                }

                //계속 이벤트 처리를 하겠다는 의미.
                return true;
            }
            case MotionEvent.ACTION_MOVE:
            {
                if(i>2){
                    i = 0;
                }
                //포인트가 이동될때 마다 두 좌표(이전에눌렀던 좌표와 현재 이동한 좌료)간의 간격을 구한다.
                float dx = Math.abs(x - oldX);
                float dy = Math.abs(y - oldY);

                //두 좌표간의 간격이 4px이상이면 (가로든, 세로든) 그리기 bitmap에 선을 그린다.
                if (dx >= 4 || dy >= 4)
                {
                    //path에 좌표의 이동 상황을 넣는다. 이전 좌표에서 신규 좌표로..
                    //lineTo를 쓸수 있지만.. 좀더 부드럽게 보이기 위해서 quadTo를 사용함.
                    path.quadTo(oldX, oldY, x, y);


                    //포인터의 마지막 위치값을 기억한다.
                    oldX = x;
                    oldY = y;

                    //그리기 bitmap에 path를 따라서 선을 그린다.
                    canvas.drawPath(path, paint);

                }

                //알파벳의 시작 포인트(점) 과 터치시 시작점이 일치하는가?
                if(expo(alphabet_point[i].x,(int)oldX,alphabet_point[i].y,(int)oldY,70)){
                    Toast.makeText(getContext(), i+"번째점 완료", Toast.LENGTH_SHORT).show();
                    i++;
                    //알파벳의 시작 포인트의 배열을 3개로 줬기때문에 i=3이되면 배열주소를 초과하므로 초기화시켜줌)
                    if(i>2) i = 0;
                }
                //화면을 갱신시킴.. 이 함수가 호출 되면 onDraw 함수가 실행됨.
                invalidate();
                //계속 이벤트 처리를 하겠다는 의미.
                return true;
            }
        }

        //더이상 이벤트 처리를 하지 않겠다는 의미.
        return false;
    }

    /**
     * 펜 색상 세팅
     * @param color 색상
     */
    public void setLineColor(int color)
    {
        paint = new Paint();
        paint.setColor(color);

        paint.setAlpha(255);
        paint.setDither(true);
        paint.setStrokeWidth(50); // 선의 두께 조절함
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    public void ClearCanvas(){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }


    /*public void drawTriangle() {
        float x = getRootView().getX()/2;
        float y = getRootView().getY()/2;
        drawTriangle(x, y);
    }*/

    public void drawTriangle(float x, float y) {
        setLineColor(Color.RED);
        int side = 200;  /** 삼각형 크기 */
        int height = 300;
        y = y - height;

        alphabet.reset();

        alphabet_point[0] = new Point((int)x, (int)y);        // 왼
        alphabet_point[1] = new Point((int)x-side, (int)y+height);  // 아래
        alphabet_point[2] = new Point((int)x+side, (int)y+height); // 오른
        alphabet.moveTo(alphabet_point[0].x, alphabet_point[0].y);
        alphabet.lineTo(alphabet_point[1].x, alphabet_point[1].y);
        alphabet.lineTo(alphabet_point[2].x, alphabet_point[2].y);
        alphabet.lineTo(alphabet_point[0].x, alphabet_point[0].y);
        canvas.drawPath(alphabet, paint);
        invalidate();

    }

    public boolean  expo(int b_x, int a_x,int b_y, int a_y,int stroke){

        int result_x = (b_x-a_x) * (b_x-a_x);
        int result_y = (b_y-a_y) * (b_y-a_y);
        int result = result_x+result_y;

        if(Math.sqrt(result) <= stroke){
            return  true;
        }
        else
            return false ;
    }
}

2. word matching (단어 )
- 2가지 기능을 추가하였다.
1) Number matching
2) Animal matching



