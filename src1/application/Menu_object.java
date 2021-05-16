/* This is a code for application named as "Prezoom". Which is a presentation application which is supposed to
 * create a presentation and play it.
 * 
 * We have created a large pane where user can input multiple shapes as well as text areas. User can freely
 * move around the pane to switch between multiple states, which is identified by red rectangular region on 
 * pane. 
 * 
 * We have chosen an approach where shapes can move freely around different states by using relocation 
 * animation. So, one shape can be in multiple states but at different time. Each presentation has a time-line
 * which stores animations for each shapes on pane. During particular time-line only properties will be 
 * altered for different shapes creating animation, while the shape remains the same only its property changes.  
 * 
 * Following code is created with functional approach.
 * */

package application;
	
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Menu_object extends Application 
{
	double orgSceneX, orgSceneY;
	double drawX,drawY;
	double centX,centY;
	double relocate_x,relocate_y;
	int ani_s,ani_p,j,state,k=0;
	Group root = new Group();
	GridPane obj_info = new GridPane();
	GridPane text_edit = new GridPane();
	GridPane animation = new GridPane();
	Pane canvas = new Pane();
	ScrollPane scrollPane = new ScrollPane();
	ScrollPane scrollPane1 = new ScrollPane();
	Region region = new Region();
	Region boarder = new Region();
	VBox vbox = new VBox(); 
	VBox rvbox = new VBox();
	Insets ins = new Insets(25,25,25,25);
	ArrayList<TextArea> t_area = new ArrayList<TextArea>();
	TabPane tabPane = new TabPane();
	SequentialTransition seqt = new SequentialTransition();
	SequentialTransition seq = new SequentialTransition();
	ParallelTransition  pt = new ParallelTransition() ;
	PerspectiveCamera camera = new PerspectiveCamera(true);
	Stage s = new Stage();
	Circle sel = new Circle ();
	Boolean object_select= false;
	RadioButton rb1 = new RadioButton("SequentialTransition");
	RadioButton rb2 = new RadioButton("ParallelTransition");
	ToggleGroup tg = new ToggleGroup(); 
	Transition ts;
	TextField tf0=new TextField("object");
	TextField tf1=new TextField("0");  
    TextField tf2=new TextField("0");  
    TextField tf3=new TextField("0");  
    TextField tf4=new TextField("0");
    TextField tf5=new TextField("0"); 
    TextField tf6=new TextField("BROWN"); 
    TextField tf7=new TextField("0");
    TextField tf8=new TextField("0"); 
    TextField tf9=new TextField("0"); 
    TextField tf10=new TextField("0");
    TextField tf12=new TextField("0"); 
    TextField tf13=new TextField("0");
    Button submit = new Button("Submit");
    Button edit = new Button("edit");
    Button submit_t = new Button("Submit");
	Button b1 = new Button();
	GridPane Animation_pane = new GridPane();
	TextField a_t = new TextField();
	protected Object Region_area;
	Timeline time = new Timeline();
	Scene sce;
	ArrayList<Shape> all_shape = new ArrayList<Shape>();
	ArrayList<Region_area> stage =  new ArrayList<Region_area>(20);
	Scene scene= new Scene(root,500,500);
	ColorPicker cp = new ColorPicker(Color.WHITE); 
	ChoiceBox<String> cb = new ChoiceBox<>();
	Stage primaryStage = new Stage();
	
	//VIEW
	//setting layout of main-stage. Which holds pane, grid-pane,h-box and v-box.
	@Override
	public void start(Stage primarySTage) 
	{
		drawX = 100;
		drawY = 100;
		canvas.setPrefSize(10000, 700);
		boarder.getStylesheets().add(Menu_object.class.getResource("application.css").toExternalForm());
		boarder.getStyleClass().add("boarder_region");
		boarder.setMinSize(800, 500);
		boarder.setPadding(ins);
		boarder.toFront();
		boarder.setLayoutX(drawX);
		boarder.setLayoutY(drawY);
		boarder.setMouseTransparent(true);
		Region_area reg = new Region_area();
		reg.region_x=drawX;
		reg.region_y=drawY;
		rb1.setToggleGroup(tg);
	    rb2.setToggleGroup(tg);
	    tg.selectToggle(rb1);
		time.setCycleCount(Timeline.INDEFINITE);
		try 
		{
			primaryStage.setTitle("PREZOOM");
			scrollPane.setPrefSize(1000, 775);
			scrollPane.setLayoutY(25);
			scrollPane.setLayoutX(200);
			scrollPane.setContent(canvas);
			scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
	        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	        scrollPane.getStylesheets().add(Menu_object.class.getResource("application.css").toExternalForm());
	        scrollPane.getStyleClass().add("scrollPane");
	        
	        scrollPane1.setPrefSize(1000, 775);
			scrollPane1.setLayoutY(25);
			scrollPane1.setLayoutX(0);
			scrollPane1.setContent(rvbox);
			scrollPane1.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
	        scrollPane1.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	        //creating menu-bar by calling menu_bar() function.
			MenuBar menuBar = menu_bar();
			vbox.prefWidthProperty().bind(primaryStage.widthProperty());
			vbox.getChildren().add(menuBar);
			//calling obj_info_menu for creating obj_info_menu 
			obj_info_menu();
			text_editor();
			//call_rvbox(b1);
			animation_view();
			rvbox.setLayoutX(50);
			rvbox.setLayoutY(75);
			Animation_pane.setLayoutX(1250);
			Animation_pane.setLayoutY(400);
			root.getChildren().addAll(vbox,scrollPane1,scrollPane,obj_info,text_edit,rvbox,Animation_pane,tabPane);
						
		     Tab shape_info = new Tab("shape_information");
		     Tab text_editor = new Tab("text_editor");
		     Tab Animation = new Tab("animation");
		    
		     tabPane.setLayoutX(1200);
		     tabPane.setLayoutY(25);
		     tabPane.setPrefWidth(335);
		     tabPane.getTabs().addAll(shape_info,text_editor,Animation);
		     shape_info.setContent(obj_info);
		     text_editor.setContent(text_edit);
		     Animation.setContent(animation);
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
	
	//VIEW
	//A function for creating region button which can be used to create new state or goto particular state
	private void call_rvbox(Button b1, Region_area reg) 
	{
	    b1.setText("State "+(k));
		b1.setPrefSize(200, 50);
		b1.getStylesheets().add(Menu_object.class.getResource("application.css").toExternalForm());
		b1.getStyleClass().add("region_button");
		rvbox.getChildren().add(b1);
		b1.setOnAction((click)->{
			scrollPane.setHvalue((reg.num)*0.1);
			boarder.setLayoutX(reg.region_x);
			boarder.setLayoutY(reg.region_y);
			drawX = reg.region_x;
			drawY = reg.region_y;
		});
		b1.setOnContextMenuRequested((click)->{
			ContextMenu contextMenu1 = new ContextMenu();
			MenuItem new_state = new MenuItem("new state");
			
			new_state.setOnAction((t)->{
				Region_area region_boarder = new Region_area();
        		region_boarder.new_region(region_boarder);
			});
			contextMenu1.getItems().addAll(new_state);	 
		    contextMenu1.show(b1,click.getScreenX(), click.getScreenY());	 
		});
	}


	//CREATING MENU BAR
	//VIEW
	private MenuBar menu_bar()
	{
		MenuBar menuBar = new MenuBar();
		EventHandler<ActionEvent> action = changeTabPlacement();
		//CRATING FILE HANDLING MENU
		Menu file = new Menu("File");
		MenuItem New = new MenuItem("New");
		New.setOnAction(action);
		MenuItem Open = new MenuItem("Open");
		Open.setOnAction(action);
		MenuItem Save = new MenuItem("Save");
		Save.setOnAction(action);
		MenuItem Save_As = new MenuItem("Save As");
		Save_As.setOnAction(action);
		MenuItem Exit = new MenuItem("Exit");
		Exit.setOnAction(action);
        file.getItems().addAll(New,Open,Save,Save_As,Exit);
		
		menuBar.getMenus().add(file);
		
		//CREATING TOOLS MENU
		Menu tools = new Menu("Tools");
		MenuItem Square = new MenuItem("Square");
		Square.setOnAction(action);
		MenuItem Rectangle = new MenuItem("Rectangle");
		Rectangle.setOnAction(action);
		MenuItem Triangle = new MenuItem("Elipse");
		Triangle.setOnAction(action);
		MenuItem Circle = new MenuItem("Circle");
		Circle.setOnAction(action);
		MenuItem Polygon = new MenuItem("Polygon");
		Polygon.setOnAction(action);
		MenuItem textarea = new MenuItem("Text Area");
		textarea.setOnAction(action);
		MenuItem New_Region = new MenuItem("New Region");
		New_Region.setOnAction(action);
		tools.getItems().addAll(Square,Rectangle,Triangle,Circle,Polygon,textarea,New_Region);
		menuBar.getMenus().add(tools);
		
		//CREATING RESOURCES MENU
		Menu Animation = new Menu("Animation");
		Animation.getItems().add(new SeparatorMenuItem());
		MenuItem Play = new MenuItem("Play");
		Play.setOnAction((click)->
		{
			play_animation(seq);
		});
		Animation.getItems().addAll(Play);
		menuBar.getMenus().add(Animation);
		return menuBar;
	}

	//MODEL
	//A function for playing animation in new camera
	private void play_animation(SequentialTransition seq) {
		
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateX(700);
		camera.setTranslateY(370);
		camera.setTranslateZ(-600);
		camera.setNearClip(0.1);
		camera.setFarClip(2000.0);
		camera.setFieldOfView(45);
		Scene sce= scene; 
		canvas.getChildren().removeAll(boarder,region);
		sce.setCamera(camera);
		sce.getFocusOwner();
		seq.setRate(0.5);
		 Stage s = new Stage();
         s.setTitle("preZoom");
         s.setScene(sce);
         s.show();
         s.setMaximized(true);
		sce.setOnKeyPressed(t->
        {
        	KeyCode key = t.getCode();
        	if(key == KeyCode.ENTER)
        	{ j++;
        	  seq.play();
           	}
        	if(j%2==0)
        	{
    			seq.pause();
        	}
        	if(key == KeyCode.SPACE)
        	{
        		seq.jumpTo(Duration.millis(2000));
        	}
        	if(key == KeyCode.CAPS)
        	{
        		seq.playFromStart();
        	}
        	if(key == KeyCode.ESCAPE)
        	{
        		seq.jumpTo(Duration.millis(0));
        		sce.setCamera(null);
        		//s.close();
        		canvas.getChildren().addAll(boarder,region);
        		s.show();
        		primaryStage.close();
        	}
        });
	}

	//CONTROLLER
	//Event handler for menu-bar function
	 private EventHandler<ActionEvent> changeTabPlacement() 
	 {
		 return new EventHandler<ActionEvent>() 
	        {
	            public void handle(ActionEvent event) 
	            {
	            	Region_area reg = new Region_area();
	                MenuItem mItem = (MenuItem) event.getSource();
	                String command = mItem.getText();
	               
	                if ("New".equalsIgnoreCase(command)) 
	                {
	                    Stage new_stage = new Stage();
	                    Group root = new Group();
	                    Scene new_scene = new Scene(root,400,400);
	                    new_stage.setScene(new_scene);
	                    new_stage.show();
	                } 
	                else if ("Open".equalsIgnoreCase(command)) 
	                {
	                    System.out.println("Open");
	                } 
	                else if ("Save".equalsIgnoreCase(command)) 
	                {
	                    System.out.println("Save");
	                } 
	                else if ("Save As".equalsIgnoreCase(command)) 
	                {
	                    System.out.println("Save As");
	                } 
	                else if ("Exit".equalsIgnoreCase(command)) 
	                {
	                    exit();
	                } 
	                else if ("New Region".equalsIgnoreCase(command))
	                {
	                	Region_area region_boarder = new Region_area();
	            		region_boarder.new_region(region_boarder);
	                }
	                else
	                	draw_object((drawX+350),(drawY+300),50,command,reg);
	            }

	        };
	 }
	
	 // TO CREATE AN OBJECT_INFO PART WHICH WILL SHOW THE RADIUS, LOCATION, HEIGHT AND WIDTH OF OBJECT
	 // USER CAN ALSO USE SCROLL-BAR FOR CHANGING DIMENTION OF THE SHAPE 
	 // (still working on it...)

	//VIEW
	//A function that initialize obj_info grid pane 
	private void obj_info_menu()
			    {
			    	Text Name= new Text("Object Type");
			    	Text X_Axis= new Text("X Axis");  
				 	Text Y_Axis = new Text("Y Axis");  
				 	Text radius= new Text("Radius");
				 	Text height = new Text("Height");
				 	Text width = new Text("Width");
				 	Text colour = new Text("Colour");    				  
				    obj_info.addRow(0, Name, tf0);
				    obj_info.addRow(1, X_Axis, tf1);
				    obj_info.addRow(2, Y_Axis, tf2);  
				    obj_info.addRow(3, radius, tf3);
				    obj_info.add(tf7, 2, 3);
				    obj_info.addRow(4, height, tf4);  
				    obj_info.addRow(5, width, tf5);  
				    obj_info.addRow(8, colour, cp);
				    obj_info.addRow(10, submit);
				    obj_info.toFront();
			   }

	//VIEW
	//A function that initialize text_editor grid pane 
	private void text_editor()
			    {
			    	Text Font_type= new Text("Font Type");
			    	Text Size= new Text("Size");  
				 	Text Colour = new Text("Colour"); 
				 	Text Height = new Text("Height");
				 	Text Width = new Text("Width");
			  
				    text_edit.addRow(0, Font_type, tf8);
				    text_edit.addRow(1, Size, tf9);
				    text_edit.addRow(2, Colour, cp);
				    text_edit.addRow(3, Height,tf12);
				    text_edit.addRow(4, Width,tf13);
				    text_edit.addRow(5, submit_t);
				    text_edit.add(edit,1,5);
				    text_edit.toFront();
			   }
	
	//VIEW
	//A function that initialize animation_view grid pane 
	void animation_view()
	{
		cb.getItems().add("Zoom");
		cb.getItems().add("Fade Out");
		cb.getItems().add("Fade In");
		cb.getItems().add("Change Color");
		cb.getItems().add("Realocate");
		cb.getItems().add("Change state");
		animation.addRow(0, cb);
	}
	
	
	
	 // TO CREATE A STATE AND TRANSITION PART FOR PERTICULAR STATE 
	 class Region_area
	 {
			double region_x,region_y;
			SequentialTransition st_a = new SequentialTransition();
			ParallelTransition  pt_a = new ParallelTransition() ;
			ArrayList<Shape> shape = new ArrayList<Shape>(10);
			double hvalue;
			int num;
			private void new_region(Region_area reg) 
			 {

				canvas.getChildren().remove(boarder);
				reg.region_x = (boarder.getLayoutX()+900);
				reg.region_y = boarder.getLayoutY();
				k++;
				reg.num = k;
				boarder.relocate(reg.region_x,reg.region_y);
				drawX = reg.region_x;
				drawY = reg.region_y;
				Button b1 = new Button();
				call_rvbox(b1,reg);
				hvalue=scrollPane.getHvalue()+0.1;
				scrollPane.setHvalue(hvalue);
				canvas.getChildren().add(boarder);
			 }
	}
	 
	 //MODEL
	 //A function to exit from program
	 private void exit()
	 {
		 System.out.println("Exit");  
		 System.exit(0);
	 }
	 
	 //MODEL
	// A function that adds selected object on pane
	 private void draw_object(double x, double y, double r, String command, Region_area reg) 
	 {
		 
			if ("Square".equalsIgnoreCase(command)) 
			{
	            Shape s = new Rectangle(x, y, r, r);
	            s.setStyle("-fx-fill: WHITE; -fx-stroke: BLACK; -fx-stroke-width: 1;");
	            all_shape.add(s);
	            drag(s,command,reg);
	            reg.shape.add(s);
	            all_shape.add(s);
	            canvas.getChildren().add(s);
	        }
			else if ("Rectangle".equalsIgnoreCase(command)) 
			{
	            Shape rec = new Rectangle(x, y, 2*r, r);
	            rec.setStyle("-fx-fill: WHITE; -fx-stroke: BLACK; -fx-stroke-width: 1;");
	            canvas.getChildren().add(rec);
	            reg.shape.add(rec);
	            all_shape.add(rec);
	            drag(rec,command,reg);
	        }
			else if ("Elipse".equalsIgnoreCase(command)) 
			{	
				Shape elipse = new Ellipse(x,y,r,r/2);
				canvas.getChildren().add(elipse);
				elipse.setStyle("-fx-fill: WHITE; -fx-stroke: BLACK; -fx-stroke-width: 1;");
				reg.shape.add(elipse);
				all_shape.add(elipse);
	            drag(elipse,command,reg);
	        }
			else if ("Circle".equalsIgnoreCase(command)) 
			{
	            Shape cirCle = new Circle (x,y,r);
	            canvas.getChildren().add(cirCle);
	            cirCle.setStyle("-fx-fill: WHITE; -fx-stroke: BLACK; -fx-stroke-width: 1;");
	            reg.shape.add(cirCle);
	            all_shape.add(cirCle);
	            drag(cirCle,command,reg);
	        }
			else if ("Polygon".equalsIgnoreCase(command)) 
			{
	            Shape polygon = new Polygon();  
	            
	            //Adding coordinates to the polygon 
	            ((Polygon) polygon).getPoints().addAll(new Double[]{
	               x,y, 
	               x+20,y,
	               x+20,y+5,
	               x,y+5,
	               x,y+10,
	               x-5,y+2.5,
	               x,y-5,
	            }); 
	            centX=x+10;
	            centY=y+10;
	            canvas.getChildren().add(polygon);
	            reg.shape.add(polygon);
	            all_shape.add(polygon);
	            polygon.setStyle("-fx-fill: WHITE; -fx-stroke: BLACK; -fx-stroke-width: 1;");
	        }
			else if ("Text Area".equalsIgnoreCase(command)) 
			{
	            TextArea t = new TextArea("Write Here...\n new line");//(x,y,"This is a text sample");
	            t_area.add(t);
	            drag(t);
	            canvas.getChildren().add(t);
	            t.getStylesheets().add(Menu_object.class.getResource("application.css").toExternalForm());
	            t.getStyleClass().add("text_box");
	            t.toBack();
	            t.setLayoutX(drawX);
	            t.setLayoutY(drawY);
	        }
			
	  }
	 
	 //MODEL
	 // for select and dragging shape
	 private void drag(TextArea t)
	 {
		 t.setOnMouseClicked((click)->{
			 t.setCursor(Cursor.CLOSED_HAND);
			 t.toFront();
			 orgSceneX = click.getSceneX();
			 orgSceneY = click.getSceneY();
			 TextArea text = (TextArea) (click.getSource());
			 Font font = t.getFont();
			 update_textbox(t,font);
			 text.setLayoutX(text.getLayoutX());
			 text.setLayoutY(text.getLayoutY());  
		 });
		 t.setOnMouseDragged((click)->{

			  t.setCursor(Cursor.CLOSED_HAND);
			  double offsetX = click.getSceneX() - orgSceneX;
			  double offsetY = click.getSceneY() - orgSceneY;
			  
			  TextArea c = (TextArea) (click.getSource());
		      c.setLayoutX(c.getLayoutX() + offsetX);
		      c.setLayoutY(c.getLayoutY() + offsetY);
		      region.setLayoutX(c.getLayoutX()-100);
			  region.setLayoutY(c.getLayoutY()-100);
			  
			  orgSceneX = click.getSceneX();
			  orgSceneY = click.getSceneY();
		 });
	 }
	 
	 
	 private void drag(Shape shape, String command, Region_area reg ) 
	 {  
		  shape.setOnMousePressed((t) -> 
		  {
			  object_select = true ;
			  shape.setCursor(Cursor.CLOSED_HAND);
			  update_rvbox(obj_info,shape);
			  animation_update(shape);
			  orgSceneX = t.getSceneX();
			  orgSceneY = t.getSceneY();
			  
			  if ("Circle".equalsIgnoreCase(command)) 
			  {
			  Circle c = (Circle) (t.getSource());
		      c.setCenterX(c.getCenterX());
		      c.setCenterY(c.getCenterY());
		      region.setLayoutX(c.getCenterX()-100);
			  region.setLayoutY(c.getCenterY()-100);
			  }
		      else if ("Elipse".equalsIgnoreCase(command))
			  {
				  Ellipse eli = (Ellipse) (t.getSource());
				  eli.setCenterX(eli.getCenterX());
				  eli.setCenterY(eli.getCenterY());
				  region.setLayoutX(eli.getCenterX()-100);
				  region.setLayoutY(eli.getCenterY()-100); 
			  }
		      else if ("Rectangle".equalsIgnoreCase(command)) 
			  {
				Rectangle rec = (Rectangle) (t.getSource());
				rec.setX(rec.getX());
				rec.setY(rec.getY());
				region.setLayoutX(rec.getX()+(rec.getWidth()/2)-100);
			    region.setLayoutY(rec.getY()+(rec.getHeight()/2)-100);
			  }
		      else if ("Square".equalsIgnoreCase(command)) 
			  {
				Rectangle sqr = (Rectangle) (t.getSource());
				sqr.setX(sqr.getX());
				sqr.setY(sqr.getY());
				region.setLayoutX(sqr.getX()+(sqr.getWidth()/2)-100);
			    region.setLayoutY(sqr.getY()+(sqr.getHeight()/2)-100);
			  }
		      else if ("Polygon".equalsIgnoreCase(command)) 
			  {
				Polygon poly = (Polygon) (t.getSource());
				poly.setLayoutX(poly.getLayoutX());
				poly.setLayoutY(poly.getLayoutY());
				region.setLayoutX(poly.getLayoutX()+100);
			    region.setLayoutY(poly.getLayoutY()+100);
			    poly.toFront();
			  }
		      
			  orgSceneX = t.getSceneX();
			  orgSceneY = t.getSceneY();

		      region.getStyleClass().add("object-selected");
		      region.setMinSize(200, 200);
		      region.setMouseTransparent(true);
			      
			  StackPane root = new StackPane(region);
			  root.setPadding(new Insets(16));
			  canvas.getChildren().add(region);
			  canvas.getStylesheets().add(Menu_object.class.getResource("application.css").toExternalForm());
			  object_select = true ;
			  shape.toFront();
		  });
	 
		  
		  shape.setOnMouseDragged((i) -> 
		  {
			  shape.setCursor(Cursor.CLOSED_HAND);
			  double offsetX = i.getSceneX() - orgSceneX;
			  double offsetY = i.getSceneY() - orgSceneY;
			  
			  if ("Circle".equalsIgnoreCase(command)) 
			  {
				  Circle c = (Circle) (i.getSource());
			      c.setCenterX(c.getCenterX() + offsetX);
			      c.setCenterY(c.getCenterY() + offsetY);
			      region.setLayoutX(c.getCenterX()-100);
				  region.setLayoutY(c.getCenterY()-100);
			  }
			  else if ("Elipse".equalsIgnoreCase(command))
			  {
				  Ellipse eli = (Ellipse) (i.getSource());
				  eli.setCenterX(eli.getCenterX()+offsetX);
				  eli.setCenterY(eli.getCenterY()+offsetY);
				  region.setLayoutX(eli.getCenterX()-100);
				  region.setLayoutY(eli.getCenterY()-100); 
			  }
			  else if ("Rectangle".equalsIgnoreCase(command) || "Square".equalsIgnoreCase(command))
			  {
				Rectangle rec = (Rectangle) (i.getSource());
				rec.setX(rec.getX() + offsetX);
				rec.setY(rec.getY() + offsetY);
				region.setLayoutX(rec.getX()+(rec.getWidth()/2)-100);
			    region.setLayoutY(rec.getY()+(rec.getHeight()/2)-100);
			  }
			  else
			  {
				  Polygon p = (Polygon)(i.getSource()) ;
				  p.setLayoutX(p.getLayoutX() + offsetX);
				  p.setLayoutY(p.getLayoutY() + offsetY);
				  region.setLayoutX(p.getLayoutX());
				  region.setLayoutY(p.getLayoutY());
			  }
			  orgSceneX = i.getSceneX();
			  orgSceneY = i.getSceneY();
		  });
	    
		  shape.setOnMouseReleased((t) ->
		  {
			  shape.setCursor(Cursor.HAND);
			  shape.setOnContextMenuRequested((r)->
			  {
				  context_menu(r,shape); 
			  });			 
		  });
	    
	 }
	 
	 //CONTROLLER
	 //A function to initialize right-click menu
	 private void context_menu(ContextMenuEvent r, Shape shape) {
		// TODO Auto-generated method stub
		ContextMenu contextMenu = new ContextMenu();
        MenuItem fill_colour = new MenuItem("fill_colour");
	        fill_colour.setOnAction((click) -> 
	        {
	        	fill_colour_menu(r,shape);
	        });
        MenuItem Animation = new MenuItem("Animation");
	        Animation.setOnAction((click) -> 
	        {
	        	Animation_menu(r,shape);	
	        });
        MenuItem Front = new MenuItem("Front");
        	Front.setOnAction((click)->
	        {
	           	shape.toFront();
	        });
	    MenuItem Back = new MenuItem("Back");
	    	Back.setOnAction((click)->
	        {
	           	shape.toBack();
	        });
        MenuItem Delete = new MenuItem("Delete");
	        Delete.setOnAction((d)->
	        {
	        	region.getStyleClass().clear();
	        	object_select = false ;
	        	delete(shape);
	        });
        contextMenu.getItems().addAll(fill_colour,Animation,Front,Back,Delete);
        contextMenu.show(shape, r.getScreenX(), r.getScreenY());
	}

	 //CONTROLLER
	 //A function to initialize change in colour menu on right click event
	 private void fill_colour_menu(ContextMenuEvent r, Shape shape) 
	 {
		// TODO Auto-generated method stub
		ContextMenu contextMenu1 = new ContextMenu();
		MenuItem red = new MenuItem("red");
		MenuItem yellow = new MenuItem("yellow");
		MenuItem black = new MenuItem("black");
		MenuItem blue = new MenuItem("blue");
		MenuItem lightblue = new MenuItem("lightblue");
		MenuItem green = new MenuItem("green");
		red.setOnAction((paint)->{fill_colour(shape,paint);});
		yellow.setOnAction((paint)->{fill_colour(shape,paint);});
		black.setOnAction((paint)->{fill_colour(shape,paint);});
		blue.setOnAction((paint)->{fill_colour(shape,paint);});
		lightblue.setOnAction((paint)->{fill_colour(shape,paint);});
		green.setOnAction((paint)->{fill_colour(shape,paint);});
		contextMenu1.getItems().addAll(red,yellow,black,blue,lightblue,green);
	    contextMenu1.show(shape,r.getScreenX(), r.getScreenY());	 
	}

	 //CONTROLLER
	 //A function to initialize animation menu on right click event
	 private void Animation_menu(ContextMenuEvent r, Shape shape) {
		// TODO Auto-generated method stub
		ContextMenu Animation_menu = new ContextMenu();
		MenuItem zoom = new MenuItem("zoom");
		MenuItem relocate = new MenuItem("relocate");
		MenuItem visible = new MenuItem("visible");
		MenuItem invisible = new MenuItem("invisible");
		MenuItem change_colour = new MenuItem("fill colour");
		MenuItem Region_change = new MenuItem("Region change");
		zoom.setOnAction((Ani) -> {animation(shape,Ani);});
		relocate.setOnAction((Ani) -> {animation(shape,Ani);});
		visible.setOnAction((Ani) -> {animation(shape,Ani);});
		invisible.setOnAction((Ani) -> {animation(shape,Ani);});
		change_colour.setOnAction((Ani) -> {animation(shape,Ani);});
		Region_change.setOnAction((Ani) -> {animation(shape,Ani);});
		Animation_menu.getItems().addAll(zoom,relocate,visible,invisible,change_colour,Region_change);
		Animation_menu.show(shape,r.getScreenX(), r.getScreenY());
	}
	 
	 //CONTROLLER
	 private void update_textbox(TextArea t, Font font)
	{
		tf8.setText(font.getStyle());
		tf9.setText(Double.toString(font.getSize()));
		tf12.setText(Double.toString(t.getHeight()));
		tf13.setText(Double.toString(t.getWidth()));
		submit_t.setOnAction((click->{
			get_values_text_box(t);
		}));
	}
	
	 //CONTROLLER
     private void update_rvbox(GridPane obj_inf, Shape shape ) {
		// TODO Auto-generated method
		// GridPane obj_info = obj_inf;
		
		if(shape.getClass().getSimpleName().equals("Rectangle"))
		{
			tf0.setText(shape.getClass().getSimpleName());
			Rectangle rec = (Rectangle) shape;
			tf1.setText(Double.toString(rec.getX()));
			tf2.setText(Double.toString(rec.getY()));
			tf4.setText(Double.toString(rec.getHeight()));
			tf5.setText(Double.toString(rec.getWidth()));
			tf6.setText(Double.toString(rec.getLayoutY()));
			cp.setValue((Color) rec.getFill());
			obj_info.getChildren().removeAll(tf3);
			submit.setOnAction((click->{
				get_values(shape);
			}));
		}
		else if(shape.getClass().getSimpleName().equals("Ellipse"))
		{
			tf0.setText(shape.getClass().getSimpleName());
			Ellipse rec = (Ellipse) shape;
			tf1.setText(Double.toString(rec.getCenterX()));
			tf2.setText(Double.toString(rec.getCenterY()));
			tf3.setText(Double.toString(rec.getRadiusX()));
			tf7.setText(Double.toString(rec.getRadiusY()));
			tf6.setText(Double.toString(rec.getLayoutY()));
			cp.setValue((Color) rec.getFill());
			submit.setOnAction((click->{
				get_values(shape);
			}));
		}
		else if(shape.getClass().getSimpleName().equals("Circle"))
		{
			tf0.setText(shape.getClass().getSimpleName());
			Circle rec = (Circle) shape;
			tf1.setText(Double.toString(rec.getCenterX()));
			tf2.setText(Double.toString(rec.getCenterY()));
			tf3.setText(Double.toString(rec.getRadius()));
			tf6.setText(Double.toString(rec.getLayoutY()));
			cp.setValue((Color) rec.getFill());
			submit.setOnAction((click->{
				get_values(shape);
			}));
		}
	}

     //MODEL
     //A function that updates object info grid pane from getting properties of select objects
	 private void get_values(Shape shape) {
		// TODO Auto-generated method stub
		if(shape.getClass().getSimpleName().equals("Rectangle"))
		{
			Rectangle rec = (Rectangle) shape;
			Text t = new Text(tf0.getText());
			rec.setAccessibleText("rec");
			canvas.getChildren().add(t);
			rec.setX(Double.parseDouble(tf1.getText()));
			rec.setY(Double.parseDouble(tf2.getText()));
			rec.setHeight(Double.parseDouble(tf4.getText()));
			rec.setWidth(Double.parseDouble(tf5.getText()));
			rec.setFill((Paint)cp.getValue());
			rec.setStyle("-fx-fill:"+(Paint)cp.getValue()+";-fx-stroke: BLACK; -fx-stroke-width: 1;");
		}
		else if(shape.getClass().getSimpleName().equals("Ellipse"))
		{			
			Ellipse rec = (Ellipse) shape;
			rec.setCenterX(Double.parseDouble(tf1.getText()));
			rec.setCenterY(Double.parseDouble(tf2.getText()));
			rec.setRadiusX(Double.parseDouble(tf3.getText()));
			rec.setRadiusY(Double.parseDouble(tf7.getText()));
			rec.setFill((Paint)cp.getValue());
			rec.setStyle("-fx-fill:"+(Paint)cp.getValue()+";-fx-stroke: BLACK; -fx-stroke-width: 1;");
		}
		else if(shape.getClass().getSimpleName().equals("Circle"))
		{
			Circle rec = (Circle) shape;
			rec.setCenterX(Double.parseDouble(tf1.getText()));
			rec.setCenterY(Double.parseDouble(tf2.getText()));
			rec.setRadius(Double.parseDouble(tf3.getText()));
			rec.setFill((Paint)cp.getValue());
			rec.setStyle("-fx-fill:"+(Paint)cp.getValue()+";-fx-stroke: BLACK; -fx-stroke-width: 1;");
		}
	}
	
	 //MODEL
	 //A function that updates text area information grid
	 private void get_values_text_box(TextArea t)
	{
		Font f = Font.font(tf8.getText(), FontWeight.LIGHT , Double.parseDouble(tf9.getText()));
		t.setFont(f);
		String style = String.format("-fx-font-size:"+tf9.getText()+";-fx-text-fill: %s;", toHexString(cp.getValue()));
        t.setStyle(style);
		t.setPrefHeight(Double.parseDouble(tf12.getText()));
		t.setPrefWidth(Double.parseDouble(tf13.getText()));
	}

	 //MODEL
	 //A function to delete selected object
	 private void delete(Shape shape)
	 {
		 canvas.getChildren().remove(shape);
	 }
	 
	 //MODEL
	 //A function for changing colour of shape
	 private void fill_colour(Shape shape, ActionEvent paint ) 
	 {
		MenuItem mItem = (MenuItem) paint.getSource();
		String colour = mItem.getText();
		shape.setStyle("-fx-fill:" + colour + "; -fx-stroke: BLACK; -fx-stroke-width: 1;");
	 }
	 
	 //CONTROLLER
	 //A function that initialize animation grid pane for creating animation for selected objects
	 private void animation_update(Shape shape)
		{
			cb.setOnAction((action)->
			{
				String s = cb.getValue();
				if("Zoom".equalsIgnoreCase(s))
				{
					Text Size = new Text("Size");
					Text Time = new Text("Time");
					TextField S = new TextField("2");
					TextField T = new TextField("0.5");
					Button Submit = new Button("Submit");
					Submit.setOnAction((click)->{
						zoom_animation(shape,Double.parseDouble(S.getText()),Double.parseDouble(T.getText()));
					});
					animation.getChildren().clear();
					animation.addRow(0, cb);
					animation.addRow(1, Size, S);
					animation.addRow(2, Time, T);
					animation.addRow(3, Submit);
					animation.add(rb1, 0, 4,1,1);
					animation.addRow(4, rb2);
				}
				else if ("Fade Out".equalsIgnoreCase(s))
				{
					Text opa = new Text("Opacity");
					Text Time = new Text("Time");
					TextField S = new TextField("1");
					TextField T = new TextField("1");
					Button Submit = new Button("Submit");
					Submit.setOnAction((click)->{
						fade_out_animation(shape,Double.parseDouble(S.getText()),Double.parseDouble(T.getText()));
					});
					animation.getChildren().clear();
					animation.addRow(0, cb);
					animation.addRow(1, opa, S);
					animation.addRow(2, Time, T);
					animation.addRow(3, Submit);
					animation.add(rb1, 0, 4,1,1);
					animation.addRow(4, rb2);
				}
				else if ("Fade In".equalsIgnoreCase(s))
				{
					Text opa = new Text("Opacity");
					Text Time = new Text("Time");
					TextField S = new TextField("0");
					TextField T = new TextField("1");
					Button Submit = new Button("Submit");
					Submit.setOnAction((click)->{
						fade_out_animation(shape,Double.parseDouble(S.getText()),Double.parseDouble(T.getText()));
					});
					animation.getChildren().clear();
					animation.addRow(0, cb);
					animation.addRow(1, opa, S);
					animation.addRow(2, Time, T);
					animation.addRow(3, Submit);
					animation.add(rb1, 0, 4,1,1);
					animation.addRow(4, rb2);
				}
				else if ("Change Color".equalsIgnoreCase(s))
				{
					Text Colour = new Text("Colour");
					Text Time = new Text("Time");
					TextField T = new TextField("1");
					Button Submit = new Button("Submit");
					Submit.setOnAction((click)->{
						fill_colour_animation(shape,(Paint)cp.getValue(),Double.parseDouble(T.getText()));
					});
					animation.getChildren().clear();
					animation.addRow(0, cb);
					animation.addRow(1, Colour, cp);
					animation.addRow(2, Time, T);
					animation.addRow(3, Submit);
					animation.add(rb1, 0, 4,1,1);
					animation.addRow(4, rb2);
				}
				else if ("Realocate".equalsIgnoreCase(s))
				{
					Text X = new Text("X Axis");
					Text Y = new Text("Y Axis");
					Text Time = new Text("Time");
					TextField x_u = new TextField();
					TextField y_u = new TextField();
					TextField T = new TextField("1");
					Button Submit = new Button("Submit");
					canvas.setOnMouseClicked((click)->{
						if (click.getClickCount()==3)
						{
							x_u.setText(Double.toString(click.getX()));
							y_u.setText(Double.toString(click.getY()));
						}
					});
					Submit.setOnAction((click)->{
						realocate_animation(shape,Double.parseDouble(x_u.getText()),Double.parseDouble(y_u.getText()),Double.parseDouble(T.getText()));
					});
					animation.getChildren().clear();
					animation.addRow(0, cb);
					animation.addRow(1, X, x_u);
					animation.addRow(2, Y, y_u);
					animation.addRow(3, Time, T);
					animation.addRow(4, Submit);
					animation.add(rb1, 0, 5,1,1);
					animation.addRow(5, rb2);
				}
				else if ("Change state".equalsIgnoreCase(s))
				{
					Text state = new Text("To state");
					Text Time = new Text("Time");
					TextField S = new TextField();
					TextField T = new TextField("1");
					Button Submit = new Button("Submit");
					canvas.setOnMouseClicked((click)->{
						if (click.getClickCount()==3)
						{
							S.setText(Double.toString(click.getX()));
							T.setText(Double.toString(click.getY()));
						}
					});
					Submit.setOnAction((click)->{
						realocate_animation(shape,Double.parseDouble(S.getText()),Double.parseDouble(T.getText()));
					});
					animation.getChildren().clear();
					animation.addRow(0, cb);
					animation.addRow(1, state, S);
					animation.addRow(2, Time, T);
					animation.addRow(3, Submit);
					animation.add(rb1, 0, 4,1,1);
					animation.addRow(4, rb2);
				}
			});	
		}

	 //CONTROLLER
	 //A function that initialize menu for creating animation for selected objects from right click menu
	 private void animation(Shape shape, ActionEvent ani)
	 {
		 MenuItem mItem = (MenuItem) ani.getSource();
			String action = mItem.getText();
			
			if ("zoom".equalsIgnoreCase(action))
			{
				zoom_animation(shape,2,1);
			}
			else if  ("relocate".equalsIgnoreCase(action))
			{
				canvas.setOnMousePressed((i)->{
					if(i.getClickCount()==3)
					{
					relocate_x=update_relocate_x(i);
					relocate_y=update_relocate_y(i);			
					}
					realocate_animation(shape, relocate_x, relocate_y, 1);
				});	
			}
			else if ("visible".equalsIgnoreCase(action))
			{
				fade_in_animation(shape,1,1);				
			}
			else if ("invisible".equalsIgnoreCase(action))
			{
				fade_out_animation(shape,1,1);
			}
			else if ("fill colour".equalsIgnoreCase(action))
			{
				fill_colour_animation(shape,(Paint)Color.RED,1);
			}	
			else if ("Region change".equalsIgnoreCase(action))
			{
			
			}
	 }
	 

	 private void realocate_animation(Shape shape, double S, double T) {
			// TODO Auto-generated method stub
		 	KeyValue cha = new KeyValue (scrollPane.hvalueProperty(),S*0.1);
			KeyFrame f = new KeyFrame (Duration.seconds(T),cha);
			Timeline animation = new Timeline(f);
			animation.play();
			add_transitition(animation);
		}
	 
	 //MODEL
	 //A function that adds relocation animation for selected object
	 private void realocate_animation(Shape shape, double x, double y, double k) 
	 {
			// TODO Auto-generated method stub
		 KeyValue xCoord = null, yCoord=null;
		 	if("Ellipse".equalsIgnoreCase(shape.getClass().getSimpleName())) 
			{
			 xCoord = new KeyValue (((Ellipse) shape).centerXProperty(),  x);
			 yCoord = new KeyValue (((Ellipse) shape).centerYProperty(), y);
			}	 
			else if ("Rectangle".equalsIgnoreCase(shape.getClass().getSimpleName())) 
			{
				 xCoord = new KeyValue (((Rectangle) shape).xProperty(),  x);
				 yCoord = new KeyValue (((Rectangle) shape).yProperty(), y);
			}
			else if ( "Circle".equalsIgnoreCase(shape.getClass().getSimpleName()))
			{
				 xCoord = new KeyValue (((Circle) shape).centerXProperty(),  x);
				 yCoord = new KeyValue (((Circle) shape).centerYProperty(), y);
			}
			 KeyFrame f = new KeyFrame (Duration.seconds(k), xCoord,yCoord);
			 Timeline animation = new Timeline(f);
			 time.getKeyFrames().addAll(f);
			 animation.play();
			 add_transitition_animation(shape);
			 add_transitition(animation);
	}
	 
	 //MODEL
	 //A function that adds change in colour animation for selected object
	 private void fill_colour_animation(Shape shape, Paint c, double k) {
		// TODO Auto-generated method stub
		 KeyValue opa = new KeyValue (shape.fillProperty(),c);
		 KeyFrame f = new KeyFrame (Duration.seconds(k), opa);
		 Timeline animation = new Timeline(f);
		 time.getKeyFrames().addAll(f);
		 animation.play(); 
		 String style = String.format("-fx-stroke: BLACK; -fx-stroke-width: 1; -fx-fill: %s;", toHexString((Color)c));
		 shape.setStyle(style);
		 add_transitition_animation(shape);
		 add_transitition(animation);
	}

	 //MODEL
	 //A function that adds fade out animation for selected object
	 private void fade_out_animation(Shape shape, double i, double k) {
		// TODO Auto-generated method stub
		 KeyValue opa = new KeyValue (shape.opacityProperty(),i);
		 KeyFrame f = new KeyFrame (Duration.seconds(k), opa);
		 Timeline animation = new Timeline(f);
		 time.getKeyFrames().addAll(f);
		 animation.play();
		 add_transitition_animation(shape);
		 add_transitition(animation);
	}
	
	 //MODEL
	 //A function that adds fade in animation for selected object
	 private void fade_in_animation(Shape shape, double i, double k) {
		// TODO Auto-generated method stub
		 KeyValue opa = new KeyValue (shape.opacityProperty(),i);
		 KeyFrame f = new KeyFrame (Duration.seconds(k), opa);
		 Timeline animation = new Timeline(f);
		 time.getKeyFrames().addAll(f);
		 animation.play(); 
		 add_transitition_animation(shape);
		 add_transitition(animation);
	}

	 //MODEL
	 //A function that adds zoom animation for selected object
	 private void zoom_animation(Shape shape, double i, double d) {
		// TODO Auto-generated method stub
		 KeyValue keyValueX = new KeyValue(shape.scaleXProperty(), i);
	     KeyValue keyValueY = new KeyValue(shape.scaleYProperty(), i);
	     KeyFrame f = new KeyFrame(Duration.seconds(d),keyValueX,keyValueY);
	     Timeline animation = new Timeline(f);
	     animation.setCycleCount(2);
	     animation.setAutoReverse(true);
	     time.getKeyFrames().addAll(f);
	     animation.play(); 
	     add_transitition_animation(shape);
	     add_transitition(animation);
	}

	 //MODEL
	 //For getting X-axis for relocation animation
	 private double update_relocate_x(MouseEvent i) {
		// TODO Auto-generated method stub
			return relocate_x = i.getX();
	 }
	
	 //MODEL
	 //For getting Y-axis for relocation animation
	 private double update_relocate_y(MouseEvent i) {
		// TODO Auto-generated method stub
			return relocate_y = i.getY();
	 }
	 
	 //MODEL
	 //A function that add buttons for each timeline transition, which represents change in property 
	 //of that object
	 private void add_transitition_animation(Shape shape)
		 {
			 Button b = new Button(shape.getClass().getSimpleName());
			 b.textFillProperty();
			 b.setTextFill(Color.RED);
			 if (rb2.isSelected())
			 {
				 ani_p++;
				 Animation_pane.add(b, ani_p, ani_s);
			 }
			 else
			 {
				ani_p=0;
				ani_s++;
			 	Animation_pane.add(b,ani_p, ani_s);
			 }
			 b.setOnAction(click->{
				state = GridPane.getRowIndex(b);
				seq.playFrom(Duration.seconds(state));
				seq.pause();
			 });
		 }

	 //MODEL
	 //A function that add time-lines into sequential transition 
	 private void add_transitition(Timeline fill) 
	 {
		// TODO Auto-generated method stub
		if (rb2.isSelected())
		 {
			 pt.getChildren().add(fill);
		 }
		 else if (pt.getChildren().size()>0 && rb1.isSelected())
		 {
			 seq.getChildren().add(pt);
			 pt.getChildren().clear();
		 }
		 else
		 {
			 seq.getChildren().add(fill);
		 }
	}
	  
	 //MODEL
	 //A function for converting colour to string for css file input
	 private static String toHexString(Color color) 
	 {
		    int r = ((int) Math.round(color.getRed()     * 255)) << 24;
		    int g = ((int) Math.round(color.getGreen()   * 255)) << 16;
		    int b = ((int) Math.round(color.getBlue()    * 255)) << 8;
		    int a = ((int) Math.round(color.getOpacity() * 255));

		    return String.format("#%08X", (r + g + b + a));
	 }
	 
	 public static void main(String[] args) 
	 {
		launch(args);
	 }
}

