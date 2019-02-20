create table tbl_board(
	bno int not null auto_increment,
	title varchar(200) not null,
	content text null,
	writer varchar(50) not null,
	regdate timestamp not null default now(),
	viewcnt int default 0,
	primary key (bno)
);

alter table tbl_board add column replycnt int default 0;

create table tbl_attach(
	fullName varchar(150) not null,
	bno int not null,
	regdate timestamp default now(),
	primary key(fullName)
);

alter table tbl_attach add constraint fk_board_attach foreign key (bno) references tbl_board (bno);

update tbl_board set replycnt = (select count(rno) from tbl_reply where bno=tbl_board.bno);

insert into tbl_board (title ,content,writer)
(select title ,content,writer from tbl_board);
