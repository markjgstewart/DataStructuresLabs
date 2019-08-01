

		for (time=0;time<Integer.parseInt(args[1]);time++)  //create time in minutes
		{
			
			for (int i=0;i<needToGo;i++)
			{
				int decide = ((int)(maxStay*Math.random())+2);
				//System.out.println("decide:"+decide);
				if ( decide  > cutOff ) 
				{
					line2.enqueue(time);
					//System.out.println("Enter line 2:decide:"+decide);
				}
				else 
				{
					line1.enqueue(time);
					//System.out.println("Enter line 1:decide:"+decide);
				}
			}
			needToGo=0;

			if (--t1<=0)
			{
				if (!line1.isEmpty()) 
				{
					sum = sum + (time - line1.dequeue());
					t1 = (int)((cutOff-2)*Math.random())+2;
					//System.out.println("in bathroom1 for:"+t1);
				}
			}

			if (--t2<=0)
			{
				if (!line2.isEmpty()) 
				{
					sum = sum + (time - line2.dequeue());
					t2 = (int)((maxStay-cutOff)*Math.random())+cutOff;
					//System.out.println("in bathroom2 for:"+t2);
				}
			}

