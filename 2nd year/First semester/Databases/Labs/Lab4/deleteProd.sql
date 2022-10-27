USE Planner
GO
/****** Object:  StoredProcedure [dbo].[delete_rows]    Script Date: 1/17/2018 6:26:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE delete_rows
	-- Add the parameters for the stored procedure here
	@nb_of_rows varchar(30),
	@table_name varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	if ISNUMERIC(@nb_of_rows) != 1
	BEGIN
		print('Not a number')
		return 1 
	END
	
	SET @nb_of_rows = cast(@nb_of_rows as INT)

	declare @last_row int
	if @table_name = 'Email_address'
		begin
			set @last_row = (select MAX(id) from Email_address) - @nb_of_rows

			delete from Email_address

			delete from Email_address
			where id > @last_row
		end
		
		if @table_name = 'Contacts'
		begin
			set @last_row =(select MAX(id) from Contacts) - @nb_of_rows
			delete from Contacts
			where id > @last_row
		end

		if @table_name = 'Voicemails'
		begin
			delete from Voicemails
		end
END