//常用sql

//移动节点

001727上级改为001730

001684上级改为001730

001730   lft = 25  rgt = 26  user_id = 13

001727   lft = 23  rgt = 24  user_id = 8

truncate tree_map;

insert into tree_map select user_id, lft, rgt, p_id from user where user_type <> 1;



DROP VIEW vw_lftrgt;
CREATE VIEW `vw_lftrgt` AS select `tree_map`.`lft` AS `lft` from `tree_map` union select `tree_map`.`rgt` AS `rgt` from `tree_map`;



call r_tree_traversal('move', 8, 13);





call r_tree_traversal('move', 9, 13);


update user, tree_map as a set user.lft = a.lft, user.rgt = a.rgt, user.p_id = a.parent_id where user.user_id = a.node_id;

update user set p_game_id = '001730' where game_id = '001727';

update user set p_game_id = '001730' where game_id = '001684';







