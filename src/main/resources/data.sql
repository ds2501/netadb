INSERT INTO geinin (combi_name, debut, description, office, created_at, updated_at)
values ('チョコレートプラネット','2006','略称はチョコプラ。キングオブコント2008,2014,2018ファイナリスト。','吉本興行',NOW(3),NOW(3));

INSERT INTO neta (g_id, neta_name, description, link, created_at, updated_at)
values ((SELECT g_id from geinin where combi_name='チョコレートプラネット'),'業者','業者の装いでどうでもいいことをする。','https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiG_evl-dfuAhUSKaYKHfe6DQcQwqsBMAF6BAgBEAk&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DYQfj8XzljCo&usg=AOvVaw1F2z-X-QA8HOEbQEoDyHqd',NOW(3),NOW(3));